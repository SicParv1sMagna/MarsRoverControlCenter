package com.example.roverbackcontrolcenter.netty;

import com.example.roverbackcontrolcenter.models.entities.RoverInfo;
import com.example.roverbackcontrolcenter.models.enums.ConnectStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverSchedulerStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverAddCommand;
import com.example.roverbackcontrolcenter.netty.models.RoverLandStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverStartSending;
import com.example.roverbackcontrolcenter.repos.RoverInfoRepo;
import com.example.roverbackcontrolcenter.rover.Rover;
import com.example.roverbackcontrolcenter.utils.MathUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Component
@Slf4j
@ChannelHandler.Sharable
public class NettyClientHandler extends ChannelInboundHandlerAdapter {
    private final RoverInfoRepo roverInfoRepo;
    @Autowired
    private Rover rover;

    private final NettyClientService nettyClientService;
    @Autowired
    private ActiveChannelContainer container;

    @Autowired
    public NettyClientHandler(@Lazy NettyClientService nettyClientService,
                              RoverInfoRepo roverInfoRepo) {
        this.nettyClientService = nettyClientService;
        this.roverInfoRepo = roverInfoRepo;
    }

    @Value("${rover.id}")
    private Long roverId;
    @Value("${rover.timeToMars}")
    private Integer timeToMars;
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof RoverStartSending roverStartSending) {
            handleRoverStartSending(roverStartSending);
        }else if(msg instanceof RoverAddCommand roverAddCommand){
            rover.addCommand(roverAddCommand);
        }
    }
    @Override
    public void channelInactive(ChannelHandlerContext ctx){
        Thread thread = new Thread(() -> {
            log.warn("Соединение со станцией разорвано, пытаемся переподключиться");
            nettyClientService.connectToServer();
        });
        thread.start();
    }


    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // Обработка ошибок при чтении сообщений
        cause.printStackTrace();
        ctx.close(); // Закрытие соединения при ошибке
    }
    private void handleRoverStartSending(RoverStartSending startSending) {
        log.warn("Rover " + roverId + " sent to Mars");
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(timeToMars);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            boolean landed = MathUtil.checkEvent(90);
            RoverLandStatus roverLandStatus = new RoverLandStatus();
            roverLandStatus.setRoverId(roverId);
            roverLandStatus.setTimestamp(LocalDateTime.now());
            if (landed) {
                roverLandStatus.setStatus(RoverStatus.IN_OPERATION);
                roverLandStatus.setY(MathUtil.adjustCord(startSending.getY(), 10));
                roverLandStatus.setX(MathUtil.adjustCord(startSending.getX(), 10));
                log.warn("Rover с id " + roverId + " сел на Марс");
                rover.setXCord(roverLandStatus.getX());
                rover.setYCord(roverLandStatus.getY());
                rover.setStatus(RoverSchedulerStatus.FREE);
                RoverInfo roverInfo = new RoverInfo();
                roverInfo.setRoverId(roverId);
                roverInfo.setX(roverLandStatus.getX());
                roverInfo.setY(roverLandStatus.getY());
                roverInfo.setStatus(RoverSchedulerStatus.FREE);
                roverInfoRepo.save(roverInfo);

            } else {
                roverLandStatus.setStatus(RoverStatus.DECOMMISSIONED);
                log.warn("Rover с id " + roverId + " улетел в пустоту");
            }
            try {
                Thread.sleep(timeToMars);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            container.getActiveChannel().writeAndFlush(roverLandStatus);
        });
        thread.start();
    }

}

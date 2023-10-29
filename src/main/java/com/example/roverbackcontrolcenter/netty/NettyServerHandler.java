package com.example.roverbackcontrolcenter.netty;

import com.example.roverbackcontrolcenter.models.DTOs.response.RoverSendStatusSocketDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverInfoConnect;
import com.example.roverbackcontrolcenter.netty.models.RoverLandStatus;
import com.example.roverbackcontrolcenter.repos.RoverRepo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Component
@Slf4j
@RequiredArgsConstructor
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private final ActiveChannelManager activeChannelManager;
    private final SimpMessagingTemplate simp;
    private final RoverRepo roverRepo;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof RoverInfoConnect) {
            handleRoverInfoConnect((RoverInfoConnect) msg, ctx.channel());
        } else if (msg instanceof RoverLandStatus) {
            handleRoverLandStatus((RoverLandStatus) msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
    }

    private void handleRoverInfoConnect(RoverInfoConnect roverInfo, Channel channel) {
        activeChannelManager.addChannel(roverInfo.getRoverId(), channel);
        log.warn("Подключен новый Rover с id: " + roverInfo.getRoverId());
    }

    private void handleRoverLandStatus(RoverLandStatus roverLandStatus) {
        Rover rover = roverRepo.findById(roverLandStatus.getRoverId()).get();
        switch (roverLandStatus.getStatus()) {
            case IN_OPERATION -> handleRoverInOperation(rover, roverLandStatus);
            case DECOMMISSIONED -> handleRoverDecommissioned(rover, roverLandStatus);
        }
        roverRepo.save(rover);
        RoverSendStatusSocketDto res = RoverSendStatusSocketDto.mapFromRoverLandStatus(roverLandStatus);
        simp.convertAndSend("/rover/statusInfo", res);
    }

    private void handleRoverInOperation(Rover rover, RoverLandStatus roverLandStatus) {
        rover.setRoverStatus(RoverStatus.IN_OPERATION);
        rover.setStartOperationDate(roverLandStatus.getTimestamp());
        log.warn("Rover с id " + roverLandStatus.getRoverId() + " сел на Марс");
    }

    private void handleRoverDecommissioned(Rover rover, RoverLandStatus roverLandStatus) {
        rover.setRoverStatus(RoverStatus.DECOMMISSIONED);
        rover.setStartOperationDate(roverLandStatus.getTimestamp());
        rover.setEndOperationDate(roverLandStatus.getTimestamp());
        log.warn("Rover с id " + roverLandStatus.getRoverId() + " улетел в пустоту");
        activeChannelManager.removeChannel(roverLandStatus.getRoverId());
    }
}

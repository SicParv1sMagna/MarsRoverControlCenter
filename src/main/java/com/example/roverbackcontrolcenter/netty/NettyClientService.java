package com.example.roverbackcontrolcenter.netty;

import com.example.roverbackcontrolcenter.models.DTOs.response.RoverConnectTCPDto;
import com.example.roverbackcontrolcenter.models.enums.ConnectStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverInfoConnect;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.util.AttributeKey;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Service
@Slf4j
@RequiredArgsConstructor
@Data
public class NettyClientService {

    private final Bootstrap nettyClientBootstrap;
    private final ActiveChannelContainer container;

    @Value("${rover.id}")
    private Long roverId;
    @Value("${rover.timeToMars}")
    private Integer timeToMars;

    @PostConstruct
    public void init() {
        connectToServer();
    }

    @SneakyThrows
    public void connectToServer() {
        RoverInfoConnect roverInfo = new RoverInfoConnect();
        roverInfo.setRoverId(roverId);

        RoverConnectTCPDto res = new RoverConnectTCPDto();
        res.setConnectStatus(ConnectStatus.FAILURE);
        int attempts = 0;
        while(res.getConnectStatus().equals(ConnectStatus.FAILURE)) {
            try {
                attempts++;
                log.warn("Попытка установиться связь с сервером " + attempts);
                Channel channel = nettyClientBootstrap.connect("localhost", 8888).sync().channel();
                channel.writeAndFlush(roverInfo);
                container.setActiveChannel(channel);
                res.setConnectStatus(ConnectStatus.SUCCESS);
                res.setTimestamp(LocalDateTime.now());
                res.setAttempts(attempts);
                Thread.sleep(timeToMars);
                log.warn("Связь установлена");
            } catch (Exception e) {
                Thread.sleep(timeToMars);
                res.setAttempts(attempts);
                res.setConnectStatus(ConnectStatus.FAILURE);
                log.error("Неудачно");
            }
        }
    }
}

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
    private final NettyClientHandler nettyClientHandler;
    private Channel activeChannel;

    @PostConstruct
    public void init() {
        RoverConnectTCPDto roverConnectTCPDto = connectToServer();
        switch (roverConnectTCPDto.getConnectStatus()){
            case SUCCESS -> log.warn(roverConnectTCPDto.toString());
            case FAILURE -> log.error(roverConnectTCPDto.toString());
        }
    }

    @SneakyThrows
    public RoverConnectTCPDto connectToServer() {
        RoverInfoConnect roverInfo = new RoverInfoConnect();
        roverInfo.setRoverId(1L);

        RoverConnectTCPDto res = new RoverConnectTCPDto();
        int attempts = 0;
        while(attempts < 5) {
            try {
                attempts++;
                Channel channel = nettyClientBootstrap.connect("localhost", 8888).sync().channel();
                channel.attr(AttributeKey.valueOf("roverInfo")).set(roverInfo);
                channel.writeAndFlush(roverInfo);
                setActiveChannel(channel);
                res.setConnectStatus(ConnectStatus.SUCCESS);
                res.setTimestamp(LocalDateTime.now());
                res.setAttempts(attempts);
                return res;
            } catch (Exception e) {
                res.setAttempts(attempts);
                res.setConnectStatus(ConnectStatus.FAILURE);
            }
        }
        res.setTimestamp(LocalDateTime.now());
        return res;
    }
}

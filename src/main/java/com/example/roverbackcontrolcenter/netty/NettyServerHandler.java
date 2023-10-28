package com.example.roverbackcontrolcenter.netty;

import com.example.roverbackcontrolcenter.netty.models.RoverInfoConnect;
import com.example.roverbackcontrolcenter.netty.models.RoverSentInfo;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    @Override
    public void channelActive(ChannelHandlerContext ctx) {

    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        /*Channel channel = ctx.channel();
        RoverInfo roverInfo = (RoverInfo) channel.attr(AttributeKey.valueOf("RoverInfo")).get();
        Long roverId = roverInfo.getRoverId();
        activeChannelManager.removeChannel(roverId);*/
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof RoverInfoConnect roverInfo) {
            activeChannelManager.addChannel(roverInfo.getRoverId(), ctx.channel());
            log.warn("Подключен новый Rover с id: " + roverInfo.getRoverId());
        } else if (msg instanceof RoverSentInfo roverSentInfo) {

        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.channel().close();
    }
}

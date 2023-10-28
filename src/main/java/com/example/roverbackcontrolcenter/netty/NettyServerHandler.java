package com.example.roverbackcontrolcenter.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.AttributeKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Component
@RequiredArgsConstructor
public class NettyServerHandler extends ChannelInboundHandlerAdapter {
    private final ActiveChannelManager activeChannelManager;

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        RoverInfo roverInfo = (RoverInfo) channel.attr(AttributeKey.valueOf("RoverInfo")).get();
        Long roverId = roverInfo.getRoverId();
        System.out.println("Принял клиента " + roverId);
        activeChannelManager.addChannel(roverId, channel);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        Channel channel = ctx.channel();
        RoverInfo roverInfo = (RoverInfo) channel.attr(AttributeKey.valueOf("RoverInfo")).get();
        Long roverId = roverInfo.getRoverId();
        activeChannelManager.removeChannel(roverId);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // Обработка входящего сообщения от сервера
    }
}

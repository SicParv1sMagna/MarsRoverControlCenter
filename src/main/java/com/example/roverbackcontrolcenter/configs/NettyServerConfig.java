package com.example.roverbackcontrolcenter.configs;

import com.example.roverbackcontrolcenter.netty.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Configuration
@RequiredArgsConstructor
public class NettyServerConfig {
    private final NettyServerHandler nettyServerHandler;

    @Bean
    public ServerBootstrap nettyServerBootstrap(@Qualifier("bossGroup") EventLoopGroup bossGroup,
                                                @Qualifier("workerGroup") EventLoopGroup workerGroup) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder(), new StringEncoder());
                        ch.pipeline().addLast(nettyServerHandler);
                    }
                });
        return serverBootstrap;
    }

    @Bean(name = "bossGroup")
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean(name = "workerGroup")
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }
}

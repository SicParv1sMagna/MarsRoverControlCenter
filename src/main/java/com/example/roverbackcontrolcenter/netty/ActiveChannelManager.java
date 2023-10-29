package com.example.roverbackcontrolcenter.netty;

import io.netty.channel.Channel;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: channel manager for netty connections
 *
 * @author Vladimir Krasnov
 */
@Component
public class ActiveChannelManager {
    private final Map<Long, Channel> activeChannels = new ConcurrentHashMap<>();

    public void addChannel(Long roverId, Channel channel) {
        activeChannels.put(roverId, channel);
    }

    public void removeChannel(Long roverId) {
        activeChannels.remove(roverId);
    }

    public void sendMessageToChannel(Long roverId, Object msg) {
        Channel channel = activeChannels.get(roverId);
        if (channel != null) {
            channel.writeAndFlush(msg);
        }
    }
}

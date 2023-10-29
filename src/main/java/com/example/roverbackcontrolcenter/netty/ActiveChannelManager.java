package com.example.roverbackcontrolcenter.netty;

import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: channel manager for netty connections
 *
 * @author Vladimir Krasnov
 */
@Component
@Slf4j
public class ActiveChannelManager {
    private final Map<Long, Channel> activeChannels = new ConcurrentHashMap<>();

    public void addChannel(Long roverId, Channel channel) {
        activeChannels.put(roverId, channel);
    }

    public void removeChannel(Long roverId) {
        activeChannels.remove(roverId);
    }

    public boolean sendMessageToChannel(Long roverId, Object msg) {
        Channel channel = activeChannels.get(roverId);
        if (channel != null) {
            channel.writeAndFlush(msg);
            return true;
        } else log.warn("Rover " + roverId + " отключен, сообщение отправится при первой возможности");
        return false;
    }
}

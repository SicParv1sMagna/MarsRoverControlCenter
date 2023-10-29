package com.example.roverbackcontrolcenter.netty;

import io.netty.channel.Channel;
import lombok.*;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ActiveChannelContainer {
    private Channel activeChannel;
}

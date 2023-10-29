package com.example.roverbackcontrolcenter.netty.models;

import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverCommandEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoverAddCommand implements Serializable {
    private Long roverId;
    private RoverCommandEnum command;
    private Double x;
    private Double y;
    private CommandStatus commandStatus;
    private LocalDateTime planTime;
}

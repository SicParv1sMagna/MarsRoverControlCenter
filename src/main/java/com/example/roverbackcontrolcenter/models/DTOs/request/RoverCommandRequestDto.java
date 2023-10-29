package com.example.roverbackcontrolcenter.models.DTOs.request;

import com.example.roverbackcontrolcenter.models.entity.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverCommandEnum;
import com.example.roverbackcontrolcenter.models.enums.SentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RoverCommandRequestDto {
    private RoverCommandEnum command;

    private Double x;
    private Double y;

    public RoverCommand mapToEntity() {
        return RoverCommand.builder()
                .command(command)
                .x(x)
                .y(y)
                .sentStatus(SentStatus.NOT_SENT)
                .planTime(LocalDateTime.now())
                .commandStatus(CommandStatus.IN_PLAN)
                .build();
    }

}

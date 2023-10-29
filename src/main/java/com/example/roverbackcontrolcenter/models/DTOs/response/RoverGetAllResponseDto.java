package com.example.roverbackcontrolcenter.models.DTOs.response;

import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
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
public class RoverGetAllResponseDto {
    private Long id;

    private String name;
    private LocalDateTime startOperationDate;
    private LocalDateTime sendToOperationDate;
    private LocalDateTime endOperationDate;
    private Double speed;
    private Double maneuverability;
    private RoverStatus roverStatus;

    public static RoverGetAllResponseDto mapFromEntity(Rover rover) {
        return RoverGetAllResponseDto.builder()
                .id(rover.getId())
                .name(rover.getName())
                .startOperationDate(rover.getStartOperationDate())
                .sendToOperationDate(rover.getSendToOperationDate())
                .endOperationDate(rover.getEndOperationDate())
                .speed(rover.getSpeed())
                .maneuverability(rover.getManeuverability())
                .roverStatus(rover.getRoverStatus())
                .build();
    }
}

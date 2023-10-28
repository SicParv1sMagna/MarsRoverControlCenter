package com.example.roverbackcontrolcenter.models.DTOs.response;

import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoverCreateResponseDto {
    private Long id;

    private String name;
    private Double speed;
    private Double maneuverability;
    private RoverStatus roverStatus;

    public static RoverCreateResponseDto mapFromEntity(Rover rover) {
        return RoverCreateResponseDto.builder()
                .id(rover.getId())
                .name(rover.getName())
                .speed(rover.getSpeed())
                .maneuverability(rover.getManeuverability())
                .roverStatus(rover.getRoverStatus())
                .build();
    }
}

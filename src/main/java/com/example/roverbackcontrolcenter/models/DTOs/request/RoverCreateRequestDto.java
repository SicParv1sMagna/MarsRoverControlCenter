package com.example.roverbackcontrolcenter.models.DTOs.request;

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
public class RoverCreateRequestDto {

    private String name;
    private Double speed;
    private Double maneuverability;

    public Rover mapToEntity() {
        return Rover.builder()
                .name(name)
                .speed(speed)
                .maneuverability(maneuverability)
                .roverStatus(RoverStatus.FREE)
                .build();
    }
}

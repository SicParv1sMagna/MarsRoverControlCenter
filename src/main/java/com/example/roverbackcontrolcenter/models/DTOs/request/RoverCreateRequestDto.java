package com.example.roverbackcontrolcenter.models.DTOs.request;

import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "Field name cant be blank")
    private String name;
    @NotBlank(message = "Field speed cant be blank")
    private Double speed;
    @NotBlank(message = "Field maneuverability cant be blank")
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

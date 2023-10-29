package com.example.roverbackcontrolcenter.models.DTOs.request;

import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Validated
public class RoverCreateRequestDto {
    @NotBlank(message = "Field name cant be blank")
    private String name;
    @NotNull(message = "Field speed cant be null")
    private Double speed;
    @NotNull(message = "Field maneuverability cant be null")
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

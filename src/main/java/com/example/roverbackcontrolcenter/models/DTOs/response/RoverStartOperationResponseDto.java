package com.example.roverbackcontrolcenter.models.DTOs.response;

import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Description: dto response for rover start mission
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoverStartOperationResponseDto {
    private Long id;

    private String name;
    private LocalDateTime sendToOperationDate;
    private RoverStatus roverStatus;

    public static RoverStartOperationResponseDto mapFromEntity(Rover rover) {
        return RoverStartOperationResponseDto.builder()
                .id(rover.getId())
                .name(rover.getName())
                .sendToOperationDate(rover.getSendToOperationDate())
                .roverStatus(rover.getRoverStatus())
                .build();
    }
}

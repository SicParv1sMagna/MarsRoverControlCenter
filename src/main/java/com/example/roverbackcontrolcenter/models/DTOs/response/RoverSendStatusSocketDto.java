package com.example.roverbackcontrolcenter.models.DTOs.response;

import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverLandStatus;
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
public class RoverSendStatusSocketDto {
    private Long roverId;
    private LocalDateTime timestamp;
    private RoverStatus status;
    private Double x;
    private Double y;

    public static RoverSendStatusSocketDto mapFromRoverLandStatus(RoverLandStatus rover) {
        return RoverSendStatusSocketDto.builder()
                .roverId(rover.getRoverId())
                .timestamp(rover.getTimestamp())
                .status(rover.getStatus())
                .x(rover.getX())
                .y(rover.getY())
                .build();
    }
}

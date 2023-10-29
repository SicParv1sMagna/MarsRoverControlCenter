package com.example.roverbackcontrolcenter.netty.models;

import com.example.roverbackcontrolcenter.models.entities.MovementHistory;
import com.example.roverbackcontrolcenter.models.enums.MovementStatus;
import lombok.*;

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
public class MovementHistoryDto implements Serializable {
    private Long id;
    private Long roverId;
    private LocalDateTime timestamp;
    private Double x;
    private Double y;
    private MovementStatus movementStatus;

    public static MovementHistoryDto mapFromEntity(MovementHistory entity) {
        return MovementHistoryDto.builder()
                .id(entity.getId())
                .roverId(entity.getRoverId())
                .timestamp(entity.getTimestamp())
                .x(entity.getX())
                .y(entity.getY())
                .movementStatus(entity.getMovementStatus())
                .build();
    }
}

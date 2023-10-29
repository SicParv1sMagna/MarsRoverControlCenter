package com.example.roverbackcontrolcenter.netty.models;

import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
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
public class RoverLandStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long roverId;
    private Double x;
    private Double y;
    private LocalDateTime timestamp;
    private RoverStatus status;

}

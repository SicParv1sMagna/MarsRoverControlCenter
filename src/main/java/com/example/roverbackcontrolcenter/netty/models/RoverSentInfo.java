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
public class RoverSentInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private LocalDateTime timestamp;
    private RoverStatus status;

}

package com.example.roverbackcontrolcenter.models.DTOs.response;

import com.example.roverbackcontrolcenter.models.enums.ConnectStatus;
import lombok.*;

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
public class RoverConnectTCPDto {
    private Integer attempts;
    private ConnectStatus connectStatus;
    private LocalDateTime timestamp;
}

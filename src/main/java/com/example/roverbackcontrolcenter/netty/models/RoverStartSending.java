package com.example.roverbackcontrolcenter.netty.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoverStartSending implements Serializable {
    private static final long serialVersionUID = 1L;
    private Double x;
    private Double y;
}

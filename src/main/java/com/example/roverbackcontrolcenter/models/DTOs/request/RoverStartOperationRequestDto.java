package com.example.roverbackcontrolcenter.models.DTOs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class RoverStartOperationRequestDto {
    @NotNull
    private Double x;
    @NotNull
    private Double y;

}

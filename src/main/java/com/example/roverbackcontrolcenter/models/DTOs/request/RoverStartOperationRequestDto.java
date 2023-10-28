package com.example.roverbackcontrolcenter.models.DTOs.request;

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
public class RoverStartOperationRequestDto {
    @NotBlank
    private Double x;
    @NotBlank
    private Double y;

}

package com.example.roverbackcontrolcenter.services;

import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverStartOperationRequestDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * Description: Interface for rover service
 *
 * @author Vladimir Krasnov
 */
@Validated
public interface RoverService {
    @Transactional
    Rover createRover(@Valid RoverCreateRequestDto request);

    @Transactional
    Rover startRoverOperation(@Min(1L) Long id, @Valid RoverStartOperationRequestDto request);
}

package com.example.roverbackcontrolcenter.services;

import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
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
    Rover createRover(@Valid RoverCreateRequestDto request);

    Rover startRoverOperation(@Min(1L) Long id);
}

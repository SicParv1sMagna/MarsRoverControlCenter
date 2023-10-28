package com.example.roverbackcontrolcenter.services;

import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;

/**
 * Description: Interface for rover service
 *
 * @author Vladimir Krasnov
 */
public interface RoverService {
    Rover createRover(RoverCreateRequestDto request);
}

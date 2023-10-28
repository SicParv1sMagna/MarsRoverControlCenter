package com.example.roverbackcontrolcenter.services.Impl;

import com.example.roverbackcontrolcenter.exceptions.RoverNameExistsException;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.repos.RoverRepo;
import com.example.roverbackcontrolcenter.services.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Service
@RequiredArgsConstructor
public class RoverServiceImpl implements RoverService {

    private final RoverRepo roverRepo;

    @Override
    public Rover createRover(RoverCreateRequestDto request) {
        try {
            Rover rover = roverRepo.save(request.mapToEntity());
            return rover;
        } catch (DataIntegrityViolationException ex) {
            throw new RoverNameExistsException(request.getName());
        }

    }
}

package com.example.roverbackcontrolcenter.services.Impl;

import com.example.roverbackcontrolcenter.exceptions.RoverAlreadyOnOperationException;
import com.example.roverbackcontrolcenter.exceptions.RoverByIdNotFoundException;
import com.example.roverbackcontrolcenter.exceptions.RoverNameExistsException;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.repos.RoverRepo;
import com.example.roverbackcontrolcenter.services.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public Rover startRoverOperation(Long id) {
        Rover rover = roverRepo.findById(id)
                .orElseThrow(() -> new RoverByIdNotFoundException(id));
        RoverStatus roverStatus = rover.getRoverStatus();
        switch (roverStatus) {
            case SENT -> throw new RoverAlreadyOnOperationException(id, "Rover already sent to operation");
            case IN_OPERATION -> throw new RoverAlreadyOnOperationException(id, "Rover already on planet");
            case DECOMMISSIONED -> throw new RoverAlreadyOnOperationException(id, "Rover decommissioned");
        }
        rover.setSendToOperationDate(LocalDateTime.now());
        rover.setRoverStatus(RoverStatus.SENT);
        return roverRepo.save(rover);
    }
}
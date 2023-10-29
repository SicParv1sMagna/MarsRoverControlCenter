package com.example.roverbackcontrolcenter.services.Impl;

import com.example.roverbackcontrolcenter.exceptions.RoverAlreadyOnOperationException;
import com.example.roverbackcontrolcenter.exceptions.RoverByIdNotFoundException;
import com.example.roverbackcontrolcenter.exceptions.RoverDisconnectedException;
import com.example.roverbackcontrolcenter.exceptions.RoverNameExistsException;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverStartOperationRequestDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.entity.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.netty.ActiveChannelManager;
import com.example.roverbackcontrolcenter.netty.models.RoverAddCommand;
import com.example.roverbackcontrolcenter.netty.models.RoverStartSending;
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
    private final ActiveChannelManager activeChannelManager;

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
    public RoverAddCommand addCommand(RoverCommand roverCommand) {
        RoverAddCommand roverAddCommand = new RoverAddCommand();
        roverAddCommand.setRoverId(roverCommand.getRoverId());
        roverAddCommand.setCommand(roverCommand.getCommand());
        roverAddCommand.setX(roverCommand.getX());
        roverAddCommand.setY(roverCommand.getY());
        roverAddCommand.setCommandStatus(roverCommand.getCommandStatus());
        roverAddCommand.setPlanTime(roverCommand.getPlanTime());
        boolean canSent = activeChannelManager.sendMessageToChannel(roverCommand.getRoverId(), roverAddCommand);
        if (canSent) {
            return roverAddCommand;
        } else throw new RoverDisconnectedException(roverCommand.getRoverId());
    }

    @Override
    public Rover startRoverOperation(Long id, RoverStartOperationRequestDto request) {
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
        Double x = request.getX();
        Double y = request.getY();
        RoverStartSending msg = new RoverStartSending();
        msg.setX(x);
        msg.setY(y);
        activeChannelManager.sendMessageToChannel(id, msg);

        return roverRepo.save(rover);
    }
}

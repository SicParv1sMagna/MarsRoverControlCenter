package com.example.roverbackcontrolcenter.controllers;

import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCommandRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverStartOperationRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.response.RoverCreateResponseDto;
import com.example.roverbackcontrolcenter.models.DTOs.response.RoverGetAllResponseDto;
import com.example.roverbackcontrolcenter.models.DTOs.response.RoverStartOperationResponseDto;
import com.example.roverbackcontrolcenter.models.entity.MovementHistory;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.models.entity.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverAddCommand;
import com.example.roverbackcontrolcenter.repos.CordRepo;
import com.example.roverbackcontrolcenter.repos.MovementRepo;
import com.example.roverbackcontrolcenter.repos.RoverCommandRepo;
import com.example.roverbackcontrolcenter.repos.RoverRepo;
import com.example.roverbackcontrolcenter.services.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;
import java.util.List;

/**
 * Description: Rover center Controller
 *
 * @author Vladimir Krasnov
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@Validated
@RequestMapping("/rover")
public class RoverCenterController {
    private final RoverService roverService;
    private final RoverRepo roverRepo;
    private final RoverCommandRepo roverCommandRepo;
    private final MovementRepo movementRepo;

    /**
     * @param request {@link RoverCreateRequestDto}
     * @return {@link RoverCreateResponseDto}
     */
    @PostMapping("/")
    public ResponseEntity<RoverCreateResponseDto> createRover(@RequestBody @Valid RoverCreateRequestDto request) {
        Rover rover = roverService.createRover(request);
        return ResponseEntity
                .created(URI.create("/api/rover/" + rover.getId()))
                .body(RoverCreateResponseDto.mapFromEntity(rover));
    }

    /**
     * @param id Long
     * @return {@link RoverStartOperationResponseDto}
     */
    @PostMapping("/{id}/startOperation")
    public ResponseEntity<RoverStartOperationResponseDto> startRoverOperation(
            @PathVariable(name = "id") @Min(value = 1L, message = "Id cant be less than 1") Long id,
            @RequestBody @Valid RoverStartOperationRequestDto request) {
        Rover rover = roverService.startRoverOperation(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RoverStartOperationResponseDto.mapFromEntity(rover));
    }

    @GetMapping("/")
    public ResponseEntity<List<RoverGetAllResponseDto>> getAll() {
        List<Rover> roverList = roverRepo.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(roverList.stream().map(RoverGetAllResponseDto::mapFromEntity).toList());
    }

    private final CordRepo cordRepo;

    @PostMapping("/{id}/commands")
    public ResponseEntity<?> addCommand(@PathVariable(name = "id") Long id,
                                        @RequestBody RoverCommandRequestDto req) {
        RoverCommand roverCommand = req.mapToEntity();
        roverCommand.setRoverId(id);
        RoverAddCommand res = roverService.addCommand(roverCommand);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(res);
    }

    @GetMapping("/{id}/history")
    public ResponseEntity<?> getHistoryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(movementRepo.findAllByRoverId(id));
    }

    @GetMapping("/{id}")
    public MovementHistory getCord1(@PathVariable(name = "id") Long id) {
        return movementRepo.findTopByRoverIdOrderByTimestampDesc(id);
    }


    private final SimpMessagingTemplate simp;

    @GetMapping("/cord")
    public ResponseEntity<?> getCordById() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cordRepo.findAll());
    }

    @GetMapping("/{id}/reset")
    public ResponseEntity<?> fun1(@PathVariable(name = "id") Long id) {
        Rover rover = roverRepo.findById(id).get();
        rover.setRoverStatus(RoverStatus.FREE);
        roverRepo.save(rover);
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }

    @GetMapping("/test")
    public void fun() {
        simp.convertAndSend("/rover/test", new Msg("hi"));
    }
}

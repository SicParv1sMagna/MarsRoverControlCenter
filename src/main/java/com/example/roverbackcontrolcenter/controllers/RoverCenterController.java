package com.example.roverbackcontrolcenter.controllers;

import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.request.RoverStartOperationRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.response.RoverCreateResponseDto;
import com.example.roverbackcontrolcenter.models.DTOs.response.RoverStartOperationResponseDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.services.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.net.URI;

/**
 * Description: Rover center Controller
 *
 * @author Vladimir Krasnov
 */
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping("/rover")
public class RoverCenterController {
    private final RoverService roverService;

    /**
     * @param request {@link RoverCreateRequestDto}
     * @return {@link RoverCreateResponseDto}
     */
    @PostMapping("/")
    private ResponseEntity<RoverCreateResponseDto> createRover(@RequestBody @Valid RoverCreateRequestDto request) {
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
    private ResponseEntity<RoverStartOperationResponseDto> startRoverOperation(
            @PathVariable(name = "id") @Min(value = 1L, message = "Id cant be less than 1") Long id,
            @RequestBody @Valid RoverStartOperationRequestDto request) {
        Rover rover = roverService.startRoverOperation(id, request);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(RoverStartOperationResponseDto.mapFromEntity(rover));
    }
}

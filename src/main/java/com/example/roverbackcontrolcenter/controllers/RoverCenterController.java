package com.example.roverbackcontrolcenter.controllers;

import com.example.roverbackcontrolcenter.models.DTOs.request.RoverCreateRequestDto;
import com.example.roverbackcontrolcenter.models.DTOs.response.RoverCreateResponseDto;
import com.example.roverbackcontrolcenter.models.entity.Rover;
import com.example.roverbackcontrolcenter.services.RoverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/")
    private ResponseEntity<RoverCreateResponseDto> createRover(@RequestBody RoverCreateRequestDto request) {
        Rover rover = roverService.createRover(request);
        return ResponseEntity
                .created(URI.create("/api/rover/" + rover.getId()))
                .body(RoverCreateResponseDto.mapFromEntity(rover));
    }
}

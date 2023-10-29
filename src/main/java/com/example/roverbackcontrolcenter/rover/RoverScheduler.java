package com.example.roverbackcontrolcenter.rover;

import com.example.roverbackcontrolcenter.models.entities.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverSchedulerStatus;
import com.example.roverbackcontrolcenter.repos.RoverCommandRepo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ScopeMetadata;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class RoverScheduler {
    private final RoverCommandRepo roverCommandRepo;
    private final Rover rover;

    @Scheduled(fixedRate = 3000) // Запуск каждые 3 секунды
    public void scheduledTask() {
        if(rover.getStatus().equals(RoverSchedulerStatus.FREE)){
            RoverCommand command = roverCommandRepo.findFirstByCommandStatusAndRoverId(CommandStatus.IN_PLAN, rover.getRoverId());
            if(command != null){
                switch (command.getCommand()){
                    case MOVE -> {
                        command.setStartTime(LocalDateTime.now());
                        rover.move(command.getX(), command.getY());
                        command.setDoneTime(LocalDateTime.now());
                        command.setCommandStatus(CommandStatus.DONE);
                        roverCommandRepo.save(command);
                        log.warn("Rover достиг место назначения");
                    }
                    case COLLECT_INFO -> {
                        command.setStartTime(LocalDateTime.now());
                        rover.collectInfo(command.getX(), command.getY());
                        command.setDoneTime(LocalDateTime.now());
                        command.setCommandStatus(CommandStatus.DONE);
                        roverCommandRepo.save(command);
                        log.warn("Исследовал место");
                    }
                }
            }
        }
    }
}

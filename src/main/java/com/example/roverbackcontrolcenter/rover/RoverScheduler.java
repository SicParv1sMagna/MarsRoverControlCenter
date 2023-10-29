package com.example.roverbackcontrolcenter.rover;

import com.example.roverbackcontrolcenter.models.entities.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverSchedulerStatus;
import com.example.roverbackcontrolcenter.repos.RoverCommandRepo;
import lombok.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Component
@RequiredArgsConstructor
public class RoverScheduler {
    private final RoverCommandRepo roverCommandRepo;
    private final Rover rover;

    @Scheduled(fixedRate = 3000) // Запуск каждые 3 секунды
    public void scheduledTask() {
        if(rover.getStatus().equals(RoverSchedulerStatus.FREE)){
            RoverCommand command = roverCommandRepo.findFirstByCommandStatus(CommandStatus.IN_PLAN);
            if(command != null){
                switch (command.getCommand()){
                    case MOVE -> rover.move(command.getX(), command.getY());
                }
            }
        }
    }
}

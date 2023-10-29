package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entities.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public interface RoverCommandRepo extends JpaRepository<RoverCommand, Long> {
    RoverCommand findFirstByCommandStatusAndRoverId(CommandStatus status, Long roverId);
}

package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entity.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.SentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public interface RoverCommandRepo extends JpaRepository<RoverCommand, Long> {
    RoverCommand findFirstByCommandStatus(CommandStatus status);

    List<RoverCommand> findAllBySentStatusOrderByIdAsc(SentStatus sentStatus);
}

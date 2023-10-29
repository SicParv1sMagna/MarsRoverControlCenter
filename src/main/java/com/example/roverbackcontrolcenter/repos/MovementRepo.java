package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entities.MovementHistory;
import lombok.*;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public interface MovementRepo extends JpaRepository<MovementHistory, Long> {
}

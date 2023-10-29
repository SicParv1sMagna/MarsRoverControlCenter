package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entities.RoverInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public interface RoverInfoRepo extends JpaRepository<RoverInfo, Long> {
    RoverInfo findByRoverId(Long id);
}

package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entity.Rover;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description: CrudRepo for rover
 *
 * @author Vladimir Krasnov
 */
public interface RoverRepo extends JpaRepository<Rover, Long> {
}

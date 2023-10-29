package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entity.Coordinate;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public interface CordRepo extends JpaRepository<Coordinate, Long> {
}

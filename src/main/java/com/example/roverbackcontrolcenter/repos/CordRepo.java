package com.example.roverbackcontrolcenter.repos;

import com.example.roverbackcontrolcenter.models.entities.Coordinate;
import io.swagger.models.auth.In;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public interface CordRepo extends JpaRepository<Coordinate, Long> {
    Coordinate findCoordinateByXAndY(Integer x, Integer y);
}

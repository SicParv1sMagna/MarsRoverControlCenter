package com.example.roverbackcontrolcenter.models.entity;

import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Description: Rover entity class
 * for detail information about rovers
 *
 * @author Vladimir Krasnov
 */
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rover {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true)
    private String name;
    @Basic
    private LocalDateTime startOperationDate;
    @Basic
    private LocalDateTime endOperationDate;
    private Double speed;
    private Double maneuverability;

    @Enumerated(EnumType.STRING)
    private RoverStatus roverStatus;
}

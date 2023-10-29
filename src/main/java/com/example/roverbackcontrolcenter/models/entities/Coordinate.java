package com.example.roverbackcontrolcenter.models.entities;

import lombok.*;

import javax.persistence.*;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Coordinate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Integer x;
    private Integer y;
    private Double obstacleTraverseCoefficient;
    private Double roubion;
    private Double montdenier;
    private Double montagnac;
    private Double salette;
    private Double robine;
    private Double swiftRun;
    private Double crosswindLake;
}

package com.example.roverbackcontrolcenter.models.entity;

import com.example.roverbackcontrolcenter.netty.models.CoordinateDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public static Coordinate mapFromCoordinateDto(CoordinateDto coordinateDto) {
        return Coordinate.builder()
                .id(coordinateDto.getId())
                .x(coordinateDto.getX())
                .y(coordinateDto.getY())
                .obstacleTraverseCoefficient(coordinateDto.getObstacleTraverseCoefficient())
                .roubion(coordinateDto.getRoubion())
                .montdenier(coordinateDto.getMontdenier())
                .montagnac(coordinateDto.getMontagnac())
                .salette(coordinateDto.getSalette())
                .robine(coordinateDto.getRobine())
                .swiftRun(coordinateDto.getSwiftRun())
                .crosswindLake(coordinateDto.getCrosswindLake())
                .build();
    }
}

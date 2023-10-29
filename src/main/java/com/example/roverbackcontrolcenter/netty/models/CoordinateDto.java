package com.example.roverbackcontrolcenter.netty.models;

import com.example.roverbackcontrolcenter.models.entity.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateDto implements Serializable {
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
    private Long roverId; // Новое поле для roverId

    public static CoordinateDto mapFromEntity(Coordinate entity, Long roverId) {
        return CoordinateDto.builder()
                .id(entity.getId())
                .x(entity.getX())
                .y(entity.getY())
                .obstacleTraverseCoefficient(entity.getObstacleTraverseCoefficient())
                .roubion(entity.getRoubion())
                .montdenier(entity.getMontdenier())
                .montagnac(entity.getMontagnac())
                .salette(entity.getSalette())
                .robine(entity.getRobine())
                .swiftRun(entity.getSwiftRun())
                .crosswindLake(entity.getCrosswindLake())
                .roverId(roverId)
                .build();
    }
}

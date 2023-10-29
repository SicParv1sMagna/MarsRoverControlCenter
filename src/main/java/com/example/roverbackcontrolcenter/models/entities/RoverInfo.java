package com.example.roverbackcontrolcenter.models.entities;

import com.example.roverbackcontrolcenter.models.enums.RoverSchedulerStatus;
import lombok.*;

import javax.persistence.*;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoverInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long roverId;
    private Double x;
    private Double y;
    @Enumerated(EnumType.STRING)
    private RoverSchedulerStatus status;
}

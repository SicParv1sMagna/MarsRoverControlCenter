package com.example.roverbackcontrolcenter.models.entities;

import com.example.roverbackcontrolcenter.models.enums.MovementStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

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
public class MovementHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private Long roverId;
    @Basic
    private LocalDateTime timestamp;
    private Double x;
    private Double y;
    private MovementStatus movementStatus;
}

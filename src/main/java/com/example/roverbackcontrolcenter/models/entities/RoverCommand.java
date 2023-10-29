package com.example.roverbackcontrolcenter.models.entities;

import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverCommandEnum;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
public class RoverCommand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private Long roverId;

    @Enumerated(EnumType.STRING)
    private RoverCommandEnum command;

    private Double x;
    private Double y;
    @Enumerated(EnumType.STRING)
    private CommandStatus commandStatus;
    @Basic
    private LocalDateTime planTime;
    @Basic
    private LocalDateTime startTime;
    @Basic
    private LocalDateTime doneTime;
}

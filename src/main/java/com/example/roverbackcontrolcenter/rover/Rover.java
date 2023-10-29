package com.example.roverbackcontrolcenter.rover;

import com.example.roverbackcontrolcenter.models.DTOs.response.MovementHistoryDto;
import com.example.roverbackcontrolcenter.models.entities.Coordinate;
import com.example.roverbackcontrolcenter.models.entities.MovementHistory;
import com.example.roverbackcontrolcenter.models.entities.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.CommandStatus;
import com.example.roverbackcontrolcenter.models.enums.MovementStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverSchedulerStatus;
import com.example.roverbackcontrolcenter.models.enums.RoverStatus;
import com.example.roverbackcontrolcenter.netty.ActiveChannelContainer;
import com.example.roverbackcontrolcenter.netty.models.RoverAddCommand;
import com.example.roverbackcontrolcenter.repos.CordRepo;
import com.example.roverbackcontrolcenter.repos.MovementRepo;
import com.example.roverbackcontrolcenter.repos.RoverCommandRepo;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Data
@RequiredArgsConstructor
@Component
@Slf4j
public class Rover {
    private final ActiveChannelContainer activeChannelContainer;
    private final CordRepo cordRepo;
    private final RoverCommandRepo roverCommandRepo;
    private final MovementRepo movementRepo;
    @Value("${rover.id}")
    private Long roverId;
    @Value("${rover.timeToMars}")
    private Integer timeToMars;
    private Double xCord;
    private Double yCord;
    private Double speed;
    private Double maneuverability;
    private RoverSchedulerStatus status = RoverSchedulerStatus.FREE;

    public void addCommand(RoverAddCommand roverAddCommand){
        roverAddCommand.setCommandStatus(CommandStatus.IN_PLAN);
        roverAddCommand.setPlanTime(LocalDateTime.now());

        RoverCommand roverCommand = new RoverCommand();
        roverCommand.setCommand(roverAddCommand.getCommand());
        roverCommand.setX(roverAddCommand.getX());
        roverCommand.setY(roverAddCommand.getY());
        roverCommand.setCommandStatus(roverAddCommand.getCommandStatus());
        roverCommand.setPlanTime(roverAddCommand.getPlanTime());

        roverCommandRepo.save(roverCommand);
        log.warn("Команда добавлена id " + roverCommand.getId());
    }

    public void move(Double x, Double y){
        status = RoverSchedulerStatus.IN_PROGRESS;
        try {
            Thread.sleep(timeToMars);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Текущие координаты марсохода
        Double currentX = xCord;
        Double currentY = yCord;

        // Вычисление расстояния до целевых координат
        double distance = Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2));

        while (distance > 0) {

            // Получение координаты препятствия на маршруте марсохода (на основе текущих координат)
            Coordinate coordinate = cordRepo.findCoordinateByXAndY((int) Math.round(xCord), (int) Math.round(yCord));

            // Вычисление коэффициента проходимости для данной точки
            Double k = coordinate.getObstacleTraverseCoefficient() / maneuverability;

            // Вычисление скорости в секунду марсохода на данной точке
            double speedOnPoint = speed * k;

            // Вычисление, сколько клеток марсоход пройдет за секунду
            double cellsPerSecond = speedOnPoint / distance;

            // Вычисление новых координат марсохода через секунду
            Double deltaX = (x - currentX) * cellsPerSecond;
            Double deltaY = (y - currentY) * cellsPerSecond;
            xCord = currentX + deltaX;
            yCord = currentY + deltaY;
            currentX = xCord;
            currentY = yCord;
            distance = Math.sqrt(Math.pow(x - currentX, 2) + Math.pow(y - currentY, 2));
            log.warn("Rover on x: " + currentX + " y: " + currentY);

            MovementHistory movementHistory = new MovementHistory();
            movementHistory.setMovementStatus(MovementStatus.OK);
            movementHistory.setX(xCord);
            movementHistory.setY(yCord);
            movementHistory.setTimestamp(LocalDateTime.now());
            movementHistory.setRoverId(roverId);
            movementRepo.save(movementHistory);
            activeChannelContainer.getActiveChannel().writeAndFlush(MovementHistoryDto.mapFromEntity(movementHistory));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        status = RoverSchedulerStatus.FREE;
    }
}

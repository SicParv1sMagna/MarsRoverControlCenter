package com.example.roverbackcontrolcenter.netty;

import com.example.roverbackcontrolcenter.models.entity.RoverCommand;
import com.example.roverbackcontrolcenter.models.enums.SentStatus;
import com.example.roverbackcontrolcenter.netty.models.RoverAddCommand;
import com.example.roverbackcontrolcenter.repos.RoverCommandRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Component
@RequiredArgsConstructor
public class RoverCommandScheduler {
    private final RoverCommandRepo roverCommandRepo;
    private final ActiveChannelManager activeChannelManager;

    @Scheduled(fixedRate = 5000) // Запуск каждые 5 секунд (5000 миллисекунд)
    public void scheduledTask() {
        List<RoverCommand> roverCommandList = roverCommandRepo.findAllBySentStatusOrderByIdAsc(SentStatus.NOT_SENT);
        if (!roverCommandList.isEmpty()) {
            roverCommandList.forEach(roverCommand -> {
                RoverAddCommand roverAddCommand = new RoverAddCommand();
                roverAddCommand.setRoverId(roverCommand.getRoverId());
                roverAddCommand.setCommand(roverCommand.getCommand());
                roverAddCommand.setX(roverCommand.getX());
                roverAddCommand.setY(roverCommand.getY());
                roverAddCommand.setCommandStatus(roverCommand.getCommandStatus());
                roverAddCommand.setPlanTime(roverCommand.getPlanTime());
                boolean canSent = activeChannelManager.sendMessageToChannel(roverCommand.getRoverId(), roverAddCommand);
                if (canSent) {
                    roverCommand.setSentStatus(SentStatus.SENT);
                    roverCommandRepo.save(roverCommand);
                }
            });
        }
    }
}

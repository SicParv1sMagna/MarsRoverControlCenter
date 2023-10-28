package com.example.roverbackcontrolcenter.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Slf4j
public class RoverAlreadyOnOperationException extends GlobalAppException {
    public RoverAlreadyOnOperationException(Long id, String cause) {
        super(400, "Rover with id " + id + " cant be sent cause of: " + cause);
        log.warn(message);
    }
}

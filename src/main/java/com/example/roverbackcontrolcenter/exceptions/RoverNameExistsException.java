package com.example.roverbackcontrolcenter.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Slf4j
public class RoverNameExistsException extends GlobalAppException {
    public RoverNameExistsException(String name) {
        super(422, "Rover with name " + name + " already exists!");
        log.warn(message);
    }
}

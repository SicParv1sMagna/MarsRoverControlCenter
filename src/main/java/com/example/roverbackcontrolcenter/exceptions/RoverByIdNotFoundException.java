package com.example.roverbackcontrolcenter.exceptions;

import lombok.extern.slf4j.Slf4j;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
@Slf4j
public class RoverByIdNotFoundException extends GlobalAppException {
    public RoverByIdNotFoundException(Long id) {
        super(404, "Rover with id " + id + " does not exists");
        log.warn(message);
    }
}

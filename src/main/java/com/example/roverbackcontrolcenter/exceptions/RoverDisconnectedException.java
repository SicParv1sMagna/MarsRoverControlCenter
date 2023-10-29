package com.example.roverbackcontrolcenter.exceptions;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public class RoverDisconnectedException extends GlobalAppException {
    public RoverDisconnectedException(Long id) {
        super(422, "Rover " + id + " offline now!");
    }
}

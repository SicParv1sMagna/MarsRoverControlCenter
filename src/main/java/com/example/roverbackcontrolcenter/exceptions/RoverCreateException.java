package com.example.roverbackcontrolcenter.exceptions;

/**
 * Description: exception for method save(Rover)
 *
 * @author Vladimir Krasnov
 */
public class RoverCreateException extends GlobalAppException {
    public RoverCreateException(String name) {
        super(500, "Cant create rover " + name + " cause server error");
    }
}

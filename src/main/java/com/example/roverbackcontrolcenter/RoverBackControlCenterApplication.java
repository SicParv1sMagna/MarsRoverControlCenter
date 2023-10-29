package com.example.roverbackcontrolcenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RoverBackControlCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RoverBackControlCenterApplication.class, args);
    }

}

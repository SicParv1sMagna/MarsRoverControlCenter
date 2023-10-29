package com.example.roverbackcontrolcenter.utils;

import lombok.*;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * Description:
 *
 * @author Vladimir Krasnov
 */
public class MathUtil {
    public static boolean checkEvent(int percentage) {
        if (percentage < 0 || percentage > 100) {
            throw new IllegalArgumentException("Percentage must be between 0 and 100.");
        }

        Random random = new Random();
        int randomNumber = random.nextInt(100) + 1; // Генерируем случайное число от 1 до 100.

        return randomNumber <= percentage;
    }
    public static double adjustCord(double cord, double deviation) {
        if (cord < 0.0) {
            cord = 0.0; // Если cord меньше 0, устанавливаем его в 0.
        }

        double minValue = Math.max(0.0, cord - deviation);
        double maxValue = cord + deviation;
        double newCord = minValue + Math.random() * (maxValue - minValue);

        return newCord;
    }
}

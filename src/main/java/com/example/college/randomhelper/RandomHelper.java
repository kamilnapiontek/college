package com.example.college.randomhelper;

import java.util.Random;

public class RandomHelper {
    public static int generateRandomNumberFromRange(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}

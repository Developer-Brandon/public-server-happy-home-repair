package com.dev.webApp.util;

import org.springframework.stereotype.Component;

@Component
public class NumberUtil {

    private int min = 1;

    private int max = 100;

    public int getRandomNumber() {
        return (int) ((Math.random() * (max - min)) + min);
    }
}

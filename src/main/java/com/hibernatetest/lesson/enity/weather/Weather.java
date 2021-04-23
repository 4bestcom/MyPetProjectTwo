package com.hibernatetest.lesson.enity.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Weather {

    private String temp;
    private String speedWing;

    public Weather(String temp, String speedWing) {
        this.temp = String.format("%.1f", Double.parseDouble(temp) - 273.0) + "C";
        this.speedWing = speedWing + " м/с";
    }
}

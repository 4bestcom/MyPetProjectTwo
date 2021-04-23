package com.hibernatetest.lesson.web.entity.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class WeatherDto {

    private Coord coord;
    private Weather [] weather;
    private String base;
    private Temperature main;
    private Integer visibility;
    private Wind wind;
    private Clouds clouds;
    private Integer dt;
    private Sys sys;
    private Integer timezone;
    private Integer id;
    private String name;
    private Integer cod;
}

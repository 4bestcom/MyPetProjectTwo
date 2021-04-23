package com.hibernatetest.lesson.web.entity.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Weather {

    private Integer id;
    private String main;
    private String description;
    private String icon;
}

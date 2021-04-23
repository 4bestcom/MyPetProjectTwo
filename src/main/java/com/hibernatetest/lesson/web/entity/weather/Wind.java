package com.hibernatetest.lesson.web.entity.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Wind {

    private String speed;
    private Integer deg;
    private String gust;
}

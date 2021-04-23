package com.hibernatetest.lesson.web.entity.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
public class Sys {

     private Integer type;
     private Integer id;
     private String country;
     private Integer sunrise;
     private Integer sunset;
}

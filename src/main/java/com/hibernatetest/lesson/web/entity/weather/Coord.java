package com.hibernatetest.lesson.web.entity.weather;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Coord {
    Double lon;
    Double lat;
}

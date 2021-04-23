package com.hibernatetest.lesson.web.client;

import com.hibernatetest.lesson.web.entity.weather.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "1",url = "http://api.openweathermap.org/data/2.5/weather?q=izhevsk,ru&APPID=94fc7a8383ea92b4d8f5822255f44556")
public interface WeatherClient {

    @GetMapping
    WeatherDto getWeather();
}

package com.hibernatetest.lesson.web.client;

import com.hibernatetest.lesson.config.WeatherFeignConfiguration;
import com.hibernatetest.lesson.web.entity.weather.WeatherDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "weather-feign-client", configuration = WeatherFeignConfiguration.class)
public interface WeatherClient {

    @GetMapping
    WeatherDto getWeather();
}

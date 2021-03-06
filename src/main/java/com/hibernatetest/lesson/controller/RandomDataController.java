package com.hibernatetest.lesson.controller;


import com.hibernatetest.lesson.GeneratorUsers;
import com.hibernatetest.lesson.entity.User;
import com.hibernatetest.lesson.exceptions.MyCustomException;
import com.hibernatetest.lesson.service.impl.UserServiceImpl;
import com.hibernatetest.lesson.web.client.WeatherClient;
import com.hibernatetest.lesson.web.entity.weather.WeatherDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController()
@RequestMapping("random")
public class RandomDataController {
    private final UserServiceImpl userServiceImpl;
    private final WeatherClient weatherClient;


    @GetMapping("/users")
    public void addRandomUsers() throws IOException {
        for (int i = 0; i < 5; i++) {
            User user = GeneratorUsers.createUser();
            userServiceImpl.saveUser(user);
        }
    }

    @GetMapping("/weather")
    public ResponseEntity<WeatherDto> getWeather(){
        WeatherDto weatherDto = weatherClient.getWeather();
        return Optional.ofNullable(weatherDto)
                .map(weather -> new ResponseEntity<>(weather, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("weather not found"));
    }
}

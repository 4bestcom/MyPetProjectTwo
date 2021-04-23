package com.hibernatetest.lesson.controllers;

import com.hibernatetest.lesson.enity.FeatureToggles;
import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.exceptions.MyCustomException;
import com.hibernatetest.lesson.mapper.UserMapper;
import com.hibernatetest.lesson.service.UserService;
import com.hibernatetest.lesson.web.entity.UserDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static java.util.function.Predicate.not;


@RestController
@RequiredArgsConstructor
@Api(value = "Operations")
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping("/user")
    @ApiOperation("Get user by id")
    public ResponseEntity<User> getUser(@RequestParam("uuid") UUID uuid) {
        if (FeatureToggles.OPTION_ONE.isActive()) {
            return new ResponseEntity<>(userService.getUserFromCriteria(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.getUserById(uuid), HttpStatus.OK);
    }

    @GetMapping("/users")
    @ApiOperation("Get all users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return Optional.ofNullable(allUsers)
                .filter(not(List::isEmpty))
                .map(mapper::mapListUserToListUserDto)
                .map(listUsersDto -> new ResponseEntity<>(listUsersDto, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("Users not found"));
    }

    @PostMapping("/user")
    @ApiOperation("Add user")
    public ResponseEntity<User> addUser(@RequestBody UserDto userDto) {
        User user = mapper.mapUserDtoToUser(userDto);
        return Optional.ofNullable(user)
                .map(userService::saveUser)
                .map(userSaved -> new ResponseEntity<>(userSaved, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("user not saved"));
    }
}


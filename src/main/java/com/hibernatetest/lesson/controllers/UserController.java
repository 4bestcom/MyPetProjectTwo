package com.hibernatetest.lesson.controllers;

import com.hibernatetest.lesson.enity.FeatureToggles;
import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.exceptions.MyCustomException;
import com.hibernatetest.lesson.mapper.UserMapper;
import com.hibernatetest.lesson.service.UserService;
import com.hibernatetest.lesson.web.entity.UserDto;
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
public class UserController {

    private final UserService userService;
    private final UserMapper mapper;

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam("uuid") UUID uuid) {
        if (FeatureToggles.OPTION_ONE.isActive()) {
            return new ResponseEntity<>(userService.getUserFromCriteria(uuid), HttpStatus.OK);
        }
        return new ResponseEntity<>(userService.getUserById(uuid), HttpStatus.OK);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return Optional.ofNullable(allUsers)
                .filter(not(List::isEmpty))
                .map(mapper::mapListUserToListUserDto)
                .map(listUsersDto -> new ResponseEntity<>(listUsersDto, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("Users not found"));
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestParam("passport") String passportNumber, @RequestParam("name") String name) {
        User user = new User();
        user.setPassportNumber(passportNumber);
        user.setFirstName(name);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("/user_dto")
    public ResponseEntity<User> addUserDto(@RequestBody UserDto userDto) {
        User user = mapper.mapUserDtoToUser(userDto);
        return Optional.ofNullable(user)
                .map(userService::saveUser)
                .map(userSaved -> new ResponseEntity<>(userSaved, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("user not saved"));
    }

}


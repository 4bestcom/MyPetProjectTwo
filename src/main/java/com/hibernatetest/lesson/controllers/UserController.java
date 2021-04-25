package com.hibernatetest.lesson.controllers;

import com.hibernatetest.lesson.enity.FeatureToggles;
import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.exceptions.MyCustomException;
import com.hibernatetest.lesson.web.mapper.UserDtoMapper;
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
    private final UserDtoMapper userDtoMapper;

    @GetMapping("/user/{uuid}")
    @ApiOperation("Get user by id")
    public ResponseEntity<UserDto> getUser(@PathVariable("uuid") UUID uuid) {
        if (FeatureToggles.OPTION_ONE.isActive()) {
            return userService.getUserFromCriteria(uuid)
                    .map(userDtoMapper::mapUserToUserDto)
                    .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                    .orElseThrow(() -> new MyCustomException("user id: " + uuid + " not found"));

        }
        return userService.getUserById(uuid)
                .map(userDtoMapper::mapUserToUserDto)
                .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("user id: " + uuid + " not found"));
    }

    @GetMapping("/users")
    @ApiOperation("Get all users")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> allUsers = userService.getAllUsers();
        return Optional.ofNullable(allUsers)
                .filter(not(List::isEmpty))
                .map(userDtoMapper::mapListUserToListUserDto)
                .map(listUsersDto -> new ResponseEntity<>(listUsersDto, HttpStatus.OK))
                .orElseThrow(() -> new MyCustomException("Users not found"));
    }

    @PostMapping("/user")
    @ApiOperation("Add user")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto) {
        User user = userDtoMapper.mapUserDtoToUser(userDto);
        return userService.saveUser(user)
                .map(userDtoMapper::mapUserToUserDto)
                .map(userResultDto -> new ResponseEntity<>(userResultDto, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/user/{uuid}")
    @ApiOperation("Delete user by id")
    public ResponseEntity<String> deleteUser(@PathVariable("uuid") UUID uuid) {
        userService.deleteUser(uuid);
        return new ResponseEntity<>("User deleted", HttpStatus.OK);
    }

    @PutMapping("/user")
    @ApiOperation("Update user by id")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        Optional<User> user = userService.updatedUser(userDto);
        return user
                .map(userDtoMapper::mapUserToUserDto)
                .map(userDtoResult -> new ResponseEntity<>(userDtoResult, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}


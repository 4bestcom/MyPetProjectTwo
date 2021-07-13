package com.hibernatetest.lesson.service;

import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.web.entity.UserDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {

    Optional<User> saveUser(User user);

    List<User> getAllUsers();

    Optional<User> getUserById(UUID uuid);

    Optional<User> getUserFromCriteria(UUID uuid);

    void deleteUser(UUID uuid);

    Optional<User> updatedUser(UserDto userDto);

}

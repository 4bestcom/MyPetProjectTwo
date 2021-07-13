package com.hibernatetest.lesson.service.impl;

import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.exceptions.MyCustomException;
import com.hibernatetest.lesson.mapper.UserMapper;
import com.hibernatetest.lesson.repository.UserRepository;
import com.hibernatetest.lesson.service.UserService;
import com.hibernatetest.lesson.web.entity.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> saveUser(User user) {
        log.debug("user is id {} common", user.getId());
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isEmpty()) {
            User userResult = userRepository.saveAndFlush(user);
            log.info("user has saved by id {}", userResult.getId());
            return Optional.of(userResult);
        }
        log.info("user by id {} is found, not saved", user.getId());
        throw new MyCustomException("the user already exists");
    }

    public List<User> getAllUsers() {
        List<User> allUsers = userRepository.findAll();
        log.info("size all users is: {}", allUsers.size());
        return allUsers;
    }

    public Optional<User> getUserById(UUID uuid) {
        Optional<User> user = userRepository.findById(uuid);
        log.info("user by id {}, has found: {}", uuid, user);
        return user;
    }

    public Optional<User> getUserFromCriteria(UUID uuid) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> fromUser = query.from(User.class);
        query.select(fromUser).where(criteriaBuilder.equal(fromUser.get("id"), uuid));
        TypedQuery<User> query1 = entityManager.createQuery(query);
        return Optional.of(query1.getSingleResult());
    }

    public void deleteUser(UUID uuid) {
        userRepository.deleteById(uuid);
        log.info("user by id: {}, has deleted", uuid);
    }

    public Optional<User> updatedUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        if (user.isPresent()) {
            userMapper.copyUser(user.get(), userDto);
            log.info("user by id {}, has updated", user.get().getId());
            return Optional.of(userRepository.saveAndFlush(user.get()));
        }
        log.info("user id: {} not found", userDto.getId());
        throw new MyCustomException("User id: " + userDto.getId() + " not found");
    }
}

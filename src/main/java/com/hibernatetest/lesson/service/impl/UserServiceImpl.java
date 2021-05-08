package com.hibernatetest.lesson.service.impl;

import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.exceptions.MyCustomException;
import com.hibernatetest.lesson.mapper.UserMapper;
import com.hibernatetest.lesson.repository.UserRepository;
import com.hibernatetest.lesson.service.UserService;
import com.hibernatetest.lesson.web.entity.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final EntityManager entityManager;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public Optional<User> saveUser(User user) {
        Optional<User> byId = userRepository.findById(user.getId());
        if (byId.isEmpty()) {
            User userResult = userRepository.saveAndFlush(user);
            return Optional.of(userResult);
        }
        throw new MyCustomException("the user already exists");
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(UUID uuid) {
        return userRepository.findById(uuid);
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
    }

    public Optional<User> updatedUser(UserDto userDto) {
        Optional<User> user = userRepository.findById(userDto.getId());
        if (user.isPresent()) {
            userMapper.copyUser(user.get(), userDto);
            return Optional.of(userRepository.saveAndFlush(user.get()));
        }
        throw new MyCustomException("User id: " + userDto.getId() + " not found");
    }
}

package com.hibernatetest.lesson.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.repository.UserRepository;
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
public class UserService {

    private final EntityManager entityManager;
    private final UserRepository userRepository;

    public User saveUser(User user) {
        return Optional.of(userRepository.saveAndFlush(user)).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow();
    }

    public User getUserFromCriteria(UUID uuid) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> query = criteriaBuilder.createQuery(User.class);
        Root<User> fromUser = query.from(User.class);
        query.select(fromUser).where(criteriaBuilder.equal(fromUser.get("id"), uuid));
        TypedQuery<User> query1 = entityManager.createQuery(query);
        return query1.getSingleResult();
    }

    public void deleteUser(UUID uuid) {
        userRepository.deleteById(uuid);
    }
}

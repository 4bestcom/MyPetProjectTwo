package com.hibernatetest.lesson;

import com.hibernatetest.lesson.enity.User;
import com.hibernatetest.lesson.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class LessonApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EntityManager entityManager;

    @Test
    @Rollback(value = false)
    void contextLoads() {
        assertNotNull(userRepository);
        User user = new User();
        user.setFirstName("Ivan");
        user.setPassportNumber("9444533333");
        userRepository.save(user);
    }


}

package com.hibernatetest.lesson;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class LessonApplicationTests {
    @Autowired
    private ApplicationContext context;

    @Test
    void init() {
        assertNotNull(context);
    }
}

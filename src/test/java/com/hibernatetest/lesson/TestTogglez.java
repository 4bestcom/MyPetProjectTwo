package com.hibernatetest.lesson;

import com.hibernatetest.lesson.enity.FeatureToggles;
import com.hibernatetest.lesson.service.TestTogglezService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.togglz.junit5.AllEnabled;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TestTogglez {

    @InjectMocks
    private TestTogglezService service;


    @Test
    @AllEnabled(FeatureToggles.class)
    void test() {
        assertEquals("done", service.testService());

    }
}

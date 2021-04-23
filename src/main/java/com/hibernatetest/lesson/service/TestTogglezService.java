package com.hibernatetest.lesson.service;

import com.hibernatetest.lesson.enity.FeatureToggles;
import org.springframework.stereotype.Service;

@Service
public class TestTogglezService {

    public String testService(){
        if (FeatureToggles.OPTION_ONE.isActive()){
            System.out.println("done");
            return "done";
        } else {
            System.out.println("not active");
            return "not active";
        }
    }
}

package com.hibernatetest.lesson.config;

import com.hibernatetest.lesson.enity.FeatureToggles;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.togglz.core.Feature;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.SimpleFeatureUser;
import org.togglz.core.user.UserProvider;

@Component
public class TogglezConfig {
    @Bean
    public Class<? extends Feature> getFeatureClass() {
        return FeatureToggles.class;
    }


    @Bean
    public UserProvider getUserProvider() {
        return new UserProvider() {
            @Override
            public FeatureUser getCurrentUser() {
                return new SimpleFeatureUser("admin", true);
            }
        };
    }
}

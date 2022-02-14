package com.hibernatetest.lesson.entity;

import org.togglz.core.Feature;
import org.togglz.core.annotation.Label;
import org.togglz.core.context.FeatureContext;

public enum FeatureToggles implements Feature {

    @Label("test option")
    OPTION_ONE;

    public boolean isActive() {
        return FeatureContext.getFeatureManager().isActive(this);
    }
}

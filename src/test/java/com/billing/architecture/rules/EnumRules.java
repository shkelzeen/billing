package com.billing.architecture.rules;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;

public class EnumRules {
    public static final ArchCondition<JavaClass> BE_ENUM =
            new ArchCondition<>("be enum") {
                @Override
                public void check(JavaClass javaClass, ConditionEvents conditionEvents) {
                    if (!javaClass.isEnum()) {
                        String msg = String.format("%s should be enum", javaClass.getName());
                        conditionEvents.add(SimpleConditionEvent.violated(javaClass, msg));
                    }
                }
            };

}

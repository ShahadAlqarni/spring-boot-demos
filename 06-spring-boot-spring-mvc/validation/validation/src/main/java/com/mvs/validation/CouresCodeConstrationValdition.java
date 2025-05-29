package com.mvs.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CouresCodeConstrationValdition implements ConstraintValidator<CouresCode,String> {

    private String coursePrefix;

    @Override
    public void initialize(CouresCode theCouresCode) {
        coursePrefix = theCouresCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext theConstraintValidatorContext) {
        boolean result;

        if (theCode != null) {
            result = theCode.startsWith(coursePrefix);
        } else {
            result = true;
        }
        return result;
    }
}

package com.rinesarusinovci.online_quizzes_vue_back.infrastructure;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ContainsValidator implements ConstraintValidator<Contains, String> {
    String value;

    @Override
    public void initialize(Contains constraintAnnotation) {
        value = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String text, ConstraintValidatorContext constraintValidatorContext) {
        if (text == null) {
            return true;
        }
        return text.contains(value);
    }
}

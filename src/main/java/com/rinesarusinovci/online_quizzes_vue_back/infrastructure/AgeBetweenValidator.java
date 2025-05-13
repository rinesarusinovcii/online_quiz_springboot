package com.rinesarusinovci.online_quizzes_vue_back.infrastructure;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class AgeBetweenValidator implements ConstraintValidator<AgeBetween, LocalDate> {
    int max;
    int min;

    @Override
    public void initialize(AgeBetween constraintAnnotation) {
        max = constraintAnnotation.max();
        min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext constraintValidatorContext) {
        if (birthDate == null) {
            return true;
        }
        int age = Period.between(birthDate, LocalDate.now()).getYears();
        return age >= min && age <= max;
    }
}

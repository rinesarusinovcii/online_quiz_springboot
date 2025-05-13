package com.rinesarusinovci.online_quizzes_vue_back.infrastructure;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class SameAsValidator implements ConstraintValidator<SameAs, Object> {
    private String field;
    private String sameAsField;

    @Override
    public void initialize(SameAs constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.sameAsField = constraintAnnotation.sameAsField();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        try {
            BeanWrapper wrapper = new BeanWrapperImpl(value);
            Object fieldValue = wrapper.getPropertyValue(field);
            Object sameAsFieldValue = wrapper.getPropertyValue(sameAsField);

            return fieldValue != null && fieldValue.equals(sameAsFieldValue);
        } catch (Exception e) {
            return false;
        }
    }
}
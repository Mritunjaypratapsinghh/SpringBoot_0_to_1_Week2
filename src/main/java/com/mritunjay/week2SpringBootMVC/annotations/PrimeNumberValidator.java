package com.mritunjay.week2SpringBootMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.annotation.Annotation;

public class PrimeNumberCheck implements ConstraintValidator<PrimeNumber,Integer> {

    @Override
    public boolean isValid(Integer integer, ConstraintValidatorContext constraintValidatorContext) {
        return false;
    }
}

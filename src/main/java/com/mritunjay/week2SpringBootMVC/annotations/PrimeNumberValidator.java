package com.mritunjay.week2SpringBootMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrimeNumberValidator implements ConstraintValidator<PrimeNumber,Integer> {

    @Override
    public boolean isValid(Integer input, ConstraintValidatorContext constraintValidatorContext) {
        int count = 0;
        for (int i = 1; i <= input; i++) {
            if (input % i == 0) {
                count += 1;
            }
            if (count > 2) {
                return false;
            }
        }
        return count == 2;
    }
}

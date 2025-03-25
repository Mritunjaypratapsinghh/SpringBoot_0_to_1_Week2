package com.mritunjay.week2SpringBootMVC.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Constraint(validatedBy = {PasswordValidator.class})
public @interface PasswordValidation {
    String message() default "Password must satisfies the following criteria:  " +
            "contains one uppercase letter, " +
            "contains one lowercase letter, " +
            "contains one special character, " +
            "minimum length is 10 characters, ";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

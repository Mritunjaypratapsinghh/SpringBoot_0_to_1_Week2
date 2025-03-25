package com.mritunjay.week2SpringBootMVC.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<PasswordValidation,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {
        int n = password.length();
        if(n<10) return false;
        String specialCharacters = "!@#$%^&*(),.?\":{}|<>";
        Boolean hasUpperCase=false;
        Boolean hasLowerCase=false;
        Boolean hasSpecial=false;
        Boolean hasSize = true;

        for(char ch: password.toCharArray()){
            if(Character.isUpperCase(ch)){
                hasUpperCase =true;
            }
            else if(Character.isLowerCase(ch)){
               hasLowerCase=true;
            } else if (specialCharacters.contains(Character.toString(ch))) {
                hasSpecial=true;
            }

        }

        if(hasSize&&hasSpecial&&hasLowerCase&&hasUpperCase){
            return true;
        }
        return false;
    }
}

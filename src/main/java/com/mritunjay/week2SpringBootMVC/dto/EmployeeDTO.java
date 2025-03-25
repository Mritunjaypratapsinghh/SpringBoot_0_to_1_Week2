package com.mritunjay.week2SpringBootMVC.dto;

import com.mritunjay.week2SpringBootMVC.annotations.EmployeeRoleValidation;
import com.mritunjay.week2SpringBootMVC.annotations.PasswordValidation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class EmployeeDTO {

    private Long id;
    @NotNull(message = "Required field in Employee: name")
    @NotEmpty(message = "Name of employee cannot be empty")
    @NotBlank(message = "Name of employee cannot be blank")
    @Size(min=3, max = 10, message = "Numbers of Character should be in range of [3,10]")
    private String name;
    @NotNull(message = "Required field:email")
    @Email(message = "Not a valid email")
    private String email;
    @Max(value = 80, message = "Age cannot be greater than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;
    @NotNull
    @EmployeeRoleValidation
    private String role; //ADMIN,USER
    @NotNull(message = "Salary cannot be null")
    @Positive(message = "Salary of Employee should be in Positive")
    @Digits(integer = 6,fraction = 2, message = "Salary can be in the form of XXXXXX.YY")
    @DecimalMax(value="100000.59")
    @DecimalMin(value="100.50")
    private Double salary;
    @PastOrPresent(message = "DateOfJoining field in Employee cannot be in future")
    private LocalDate dateOfJoining;
    @PasswordValidation
    private String password;

    @AssertTrue(message = "Employee should be active")
    private Boolean isActive;
}


package com.mritunjay.week2SpringBootMVC.dto;

import com.mritunjay.week2SpringBootMVC.annotations.PrimeNumber;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    @NotBlank(message = "title cannot be blank")
    private String title;
    @AssertTrue(message = "Department should be active")
    private Boolean isActive;
    private LocalDateTime createdAt;
    @PrimeNumber
    @Positive
    @Min(value =1, message ="should not be less than 1")
    @Max(value = 100, message = "should not greater than 100")
    private Integer number;

}

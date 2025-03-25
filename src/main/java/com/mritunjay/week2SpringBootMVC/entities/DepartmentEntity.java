package com.mritunjay.week2SpringBootMVC.entities;

import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity
public class Department {
    Long id;
    String title;
    Boolean isActive;
    LocalDateTime createdAt;
}

package com.mritunjay.week2SpringBootMVC.repositories;

import com.mritunjay.week2SpringBootMVC.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepositories extends JpaRepository<DepartmentEntity,Long> {
}

package com.mritunjay.week2SpringBootMVC.repositories;

import com.mritunjay.week2SpringBootMVC.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long>{

}

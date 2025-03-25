package com.mritunjay.week2SpringBootMVC.controllers;

import com.mritunjay.week2SpringBootMVC.dto.DepartmentDTO;
import com.mritunjay.week2SpringBootMVC.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping
    public List<DepartmentDTO> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping(path = "/{departmentId}")
    public DepartmentDTO getDepartmentById(@PathVariable Long departmentId) {
        return departmentService.getDepartmentById(departmentId);
    }


    @PostMapping
    public DepartmentDTO createDepartment(@Valid @RequestBody DepartmentDTO inputDepartment) {
        DepartmentDTO newDepartment = departmentService.createDepartment(inputDepartment);
        return newDepartment;
    }

    @PutMapping(path = "/{departmentId}")
    public DepartmentDTO updateDepartmentById(@Valid @PathVariable Long departmentId,@Valid @RequestBody DepartmentDTO updatedDepartment){
        DepartmentDTO department = departmentService.updateDepartmentById(departmentId,updatedDepartment);
        return department;
    }

    @DeleteMapping(path = "/{departmentId}")
    public Boolean deleteDepartmentById(@Valid @PathVariable Long departmentId){
        return departmentService.deleteDepartmentById(departmentId);
    }
}

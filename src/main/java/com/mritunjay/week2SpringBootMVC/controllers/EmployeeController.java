package com.mritunjay.week2SpringBootMVC.controllers;

import com.mritunjay.week2SpringBootMVC.dto.EmployeeDTO;
import com.mritunjay.week2SpringBootMVC.entities.EmployeeEntity;
import com.mritunjay.week2SpringBootMVC.exceptions.ResourceNotFoundException;
import com.mritunjay.week2SpringBootMVC.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(path= "/employees")
public class EmployeeController {


    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/getSecretMessage")
    public String getMySuperSecretMessage() {
        return "Secret Message: asdfal@#$DASD";
    }

    @GetMapping(path ="/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long employeeId) {
        Optional<EmployeeDTO> data = employeeService.getEmployeeById(employeeId);
        return data.map(employeeDTO-> ResponseEntity.ok(employeeDTO)).orElseThrow(() -> new ResourceNotFoundException("No such employee found with Id: "+employeeId));
    }


    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees(@RequestParam(required = false,name = "AGE") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@Valid @RequestBody EmployeeDTO inputEmployee){
        EmployeeDTO newEmployee = employeeService.createEmployee(inputEmployee);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path="/{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@Valid @PathVariable Long employeeId,@Valid @RequestBody EmployeeDTO employeeDTO){
        EmployeeDTO updatedEmployee = employeeService.updateEmployeeById(employeeId,employeeDTO);
        if(updatedEmployee==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping(path="/{employeeId}")
    public ResponseEntity<Boolean> deleteEmployeeById(@PathVariable Long employeeId){
        Boolean gotDeleted = employeeService.deleteEmployeeById(employeeId);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{employeeId}")
    public ResponseEntity<EmployeeDTO> partiallyUpdateEmployeeById(@PathVariable Long employeeId,@Valid @RequestBody Map<String, Object> updatedEmployee){
        EmployeeDTO employeeDTO =employeeService.partiallyUpdateEmployeeById(employeeId,updatedEmployee);
        if(employeeDTO==null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(employeeDTO);
    }
}

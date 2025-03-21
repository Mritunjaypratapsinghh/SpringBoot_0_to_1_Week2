package com.mritunjay.week2SpringBootMVC.controllers;

import com.mritunjay.week2SpringBootMVC.dto.EmployeeDTO;
import com.mritunjay.week2SpringBootMVC.entities.EmployeeEntity;
import com.mritunjay.week2SpringBootMVC.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public EmployeeDTO getEmployeeById(@PathVariable Long employeeId) {
        return employeeService.getEmployeeById(employeeId);
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees(@RequestParam(required = false,name = "AGE") Integer age,
                                                @RequestParam(required = false) String sortBy){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmployee){
        return employeeService.createEmployee(inputEmployee);
    }

    @PutMapping(path="/{employeeId}")
    public EmployeeDTO updateEmployeeById(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO){
        return employeeService.updateEmployeeById(employeeId,employeeDTO);
    }

    @DeleteMapping(path="/{employeeId}")
    public String deleteEmployeeById(@PathVariable Long employeeId){
        return employeeService.deleteEmployeeById(employeeId);
    }

    @PatchMapping(path = "/{employeeId}")
    public EmployeeDTO partiallyUpdateEmployeeById(@PathVariable Long employeeId, @RequestBody Map<String, Object> updatedEmployee){
        return employeeService.partiallyUpdateEmployeeById(employeeId,updatedEmployee);
    }
}

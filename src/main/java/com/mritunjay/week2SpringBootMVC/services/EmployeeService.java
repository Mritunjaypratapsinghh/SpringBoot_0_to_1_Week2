package com.mritunjay.week2SpringBootMVC.services;

import com.mritunjay.week2SpringBootMVC.dto.EmployeeDTO;
import com.mritunjay.week2SpringBootMVC.entities.EmployeeEntity;
import com.mritunjay.week2SpringBootMVC.exceptions.ResourceNotFoundException;
import com.mritunjay.week2SpringBootMVC.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PatchMapping;

import java.lang.reflect.Field;
import java.sql.Ref;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final ModelMapper modelMapper;

    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    public Optional<EmployeeDTO> getEmployeeById(Long id){
//        Optional<EmployeeDTO> employee = employeeRepository.findById(id).orElse(null);
//
//        return employee.map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class);
        return employeeRepository.findById(id).map(employeeEntity -> modelMapper.map(employeeEntity,EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees(){
        List<EmployeeEntity> allEmployees= employeeRepository.findAll();

        return allEmployees.stream().map(employeeEntity ->modelMapper.map(employeeEntity,EmployeeDTO.class)).collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO inputEmployee){
        EmployeeEntity employee = modelMapper.map(inputEmployee,EmployeeEntity.class);
        EmployeeEntity newEmployee = employeeRepository.save(employee);
        return modelMapper.map(newEmployee,EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long employeeId, EmployeeDTO employeeDTO) {
        isExistsByEmployeeId(employeeId);
        EmployeeEntity existingEmployee = employeeRepository.findById(employeeId).orElse(null);
        modelMapper.map(employeeDTO,existingEmployee);
        existingEmployee.setId(employeeId);
        EmployeeEntity savedEmployeeEntity = employeeRepository.save(existingEmployee);
        return modelMapper.map(savedEmployeeEntity,EmployeeDTO.class);
    }

    public boolean isExistsByEmployeeId(Long employeeId){
        boolean exists = employeeRepository.existsById(employeeId);
        if(!exists) throw new ResourceNotFoundException("Employee not found with id: "+employeeId);
        return true;
    }

    public Boolean deleteEmployeeById(Long employeeId){
        isExistsByEmployeeId(employeeId);
        employeeRepository.deleteById(employeeId);
        return true;
    }

    public EmployeeDTO partiallyUpdateEmployeeById(Long employeeId, Map<String, Object> updatedEmployee) {
        // Step 1: Check if the employee exists in the database.
        isExistsByEmployeeId(employeeId);

        // Step 2: Retrieve the EmployeeEntity object from the database.
        EmployeeEntity employeeEntity = employeeRepository.findById(employeeId).orElse(null);

        // Step 3: Iterate through the map of updated fields (key = field name, value = new value).
        updatedEmployee.forEach((field, value) -> {
            // Dynamically find the field in the EmployeeEntity class using reflection.
            Field fieldToBeUpdated = ReflectionUtils.findField(EmployeeEntity.class, field);

            // Make the field accessible (even if it is private).
            fieldToBeUpdated.setAccessible(true);

            // Dynamically set the value of the field for the given employeeEntity object.
            ReflectionUtils.setField(fieldToBeUpdated, employeeEntity, value);
        });

        // Step 4: Save the updated entity back to the database and return the updated DTO.
        return modelMapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }



}

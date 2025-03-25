package com.mritunjay.week2SpringBootMVC.services;

import com.mritunjay.week2SpringBootMVC.dto.DepartmentDTO;
import com.mritunjay.week2SpringBootMVC.entities.DepartmentEntity;
import com.mritunjay.week2SpringBootMVC.exceptions.ResourceNotFoundException;
import com.mritunjay.week2SpringBootMVC.repositories.DepartmentRepositories;
import jakarta.validation.Valid;
import org.h2.engine.Mode;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepositories departmentRepositories;
    private final ModelMapper modelMapper;

    public DepartmentService(DepartmentRepositories departmentRepositories, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.departmentRepositories = departmentRepositories;
    }

    public boolean departmentExists(Long departmentId){
        boolean exists = departmentRepositories.existsById(departmentId);
        if(!exists) throw new ResourceNotFoundException("Department with id: "+ departmentId +" Not found");
        return true;
    }

    public DepartmentDTO getDepartmentById(Long departmentId){
        departmentExists(departmentId);
        DepartmentEntity department = departmentRepositories.findById(departmentId).orElse(null);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> allDepartment = departmentRepositories.findAll();
        return allDepartment.stream().map(DepartmentEntity -> modelMapper.map(DepartmentEntity, DepartmentDTO.class)).collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(@Valid DepartmentDTO inputDepartment) {
        DepartmentEntity departmentEntity = modelMapper.map(inputDepartment, DepartmentEntity.class);
        DepartmentEntity newDepartment = departmentRepositories.save(departmentEntity);
        return modelMapper.map(newDepartment, DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartmentById(Long departmentId,DepartmentDTO updatedDepartment) {
        departmentExists(departmentId);
        DepartmentEntity existingDepartment = departmentRepositories.findById(departmentId).orElse(null);
        modelMapper.map(updatedDepartment,existingDepartment);
        existingDepartment.setId(departmentId);
        DepartmentEntity department = departmentRepositories.save(existingDepartment);
        return modelMapper.map(department, DepartmentDTO.class);
    }

    public boolean deleteDepartmentById(@Valid Long departmentId) {
        departmentExists(departmentId);
        departmentRepositories.deleteById(departmentId);
        return true;
    }
}

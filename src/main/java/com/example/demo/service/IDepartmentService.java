package com.example.demo.service;

import com.example.demo.entity.Department;

import java.util.List;
import java.util.Optional;

public interface IDepartmentService {
    public List<Department> getAllDepartments();
    public Optional<Department> getDepartmentById(Integer id);

    public boolean doesDepartmentExists(Integer id);
    public Department creatDepartment(Department department);
    public Department updateDepartment(Integer id, Department updatedDepartment);
    public Department deleteDepartment(Integer id);
}

package com.example.demo.service;

import com.example.demo.entity.Department;
import com.example.demo.repository.IDepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DepartmentService implements IDepartmentService{

    private final IDepartmentRepository repository;
    @Override
    public List<Department> getAllDepartments() {
        return repository.findAll();
    }

    public Optional<Department> getDepartmentById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public boolean doesDepartmentExists(Integer id) {
        return repository.existsById(id);
    }

    @Override
    public Department creatDepartment(Department department) {
        return repository.save(department);
    }

    @Override
    public Department updateDepartment(Integer id, Department updatedDepartment) {
        Optional<Department> optionalDepartment = repository.findById(id);
        if(optionalDepartment.isPresent()){
            Department department = optionalDepartment.get();
            department.setName(updatedDepartment.getName());
            department.setType(updatedDepartment.getType());
            department.setCreatedDate(updatedDepartment.getCreatedDate());
            department.setTotalMember(updatedDepartment.getTotalMember());
            return repository.save(department);
        } else {
            return null;
        }
    }

    @Override
    public Department deleteDepartment(Integer id) {
        repository.deleteById(id);
        return null;
    }
}

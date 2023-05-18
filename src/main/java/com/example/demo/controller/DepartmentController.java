package com.example.demo.controller;


import com.example.demo.entity.Department;
import com.example.demo.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/departments")
public class DepartmentController {

    @Autowired
    private IDepartmentService service;

    @GetMapping()
    public ResponseEntity<List<Department>> getAllDepartments(){
        List<Department> departments = service.getAllDepartments();
        return ResponseEntity
                .ok()
                .body(departments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Department>> getDepartmentById(@PathVariable(name="id") Integer id){

        Optional<Department> department = service.getDepartmentById(id);

        if (department != null) {
            return new ResponseEntity<>(department, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Department> createDepartment(Department department){
        Department department1 = service.creatDepartment(department);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(department1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable(name = "id") Integer id, @RequestBody Department updateDepartment){
        boolean doesDepartmentExists = service.doesDepartmentExists(id);

        if (doesDepartmentExists){
            Department department = service.updateDepartment(id, updateDepartment);
            return ResponseEntity.ok().body(department);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable(name = "id") Integer id){
        boolean doesDepartmentExists = service.doesDepartmentExists(id);

        if (doesDepartmentExists){
            Department department = service.deleteDepartment(id);
            return ResponseEntity.ok().body(department);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}

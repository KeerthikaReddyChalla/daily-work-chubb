package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student s) {
    	try {
            Student saved = service.save(s);
            return ResponseEntity.status(HttpStatus.CREATED).body(saved); 
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); 
        }
    }

    @GetMapping
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Student getOne(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Integer id, @RequestBody Student s) {

  
        Student existing = service.getById(id);
        if (existing == null) {
            return null; 
        }

        s.setId(id);

        return service.update(s);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id) {
        return service.delete(id);
    }
}

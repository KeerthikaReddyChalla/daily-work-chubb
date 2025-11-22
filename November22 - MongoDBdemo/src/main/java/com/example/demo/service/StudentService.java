package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    public Student save(Student s) {
        return repo.save(s);
    }

    public List<Student> getAll() {
        return repo.findAll();
    }

    public Student getById(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public Student update(Student s) {
        return repo.save(s);
    }

    public String delete(Integer id) {
        repo.deleteById(id);
        return "Deleted";
    }
}

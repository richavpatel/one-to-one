package com.spring.onetoone.controller;

import com.spring.onetoone.model.Student;
import com.spring.onetoone.repository.StudentRepository;

import com.spring.onetoone.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {



    @Autowired
    StudentService studentService;

    @GetMapping()
    public List<Student> findAll() {
        return studentService.findAll();
    }

    @Cacheable(cacheNames = "students", key = "#id")
    @GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id) {
        return studentService.findStudentById(id);
    }

    @PostMapping()
    public int addStudent(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @CachePut(cacheNames = "students", key = "#id")
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {

        return studentService.updateStudent(id, student);
    }

    @CacheEvict(cacheNames = "students", key = "#id")
    @DeleteMapping("/{id}")
    public int deleteStudent(@PathVariable Long id) {
        return studentService.deleteStudent(id);
    }
}

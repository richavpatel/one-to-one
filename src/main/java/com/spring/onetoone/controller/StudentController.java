package com.spring.onetoone.controller;

import com.spring.onetoone.model.Student;
import com.spring.onetoone.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

@Autowired
    StudentRepository studentRepository;

@GetMapping()
    public List<Student> findAll(){
    return studentRepository.getAll();
}
@GetMapping("/{id}")
    public Student findStudentById(@PathVariable Long id){
    return studentRepository.findById(id);
}
@PostMapping()
    public int addStudent(@RequestBody Student student){
    return studentRepository.addStudent(student);
}

@PutMapping("/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student student){

    return  studentRepository.updateStudent(id,student);
}

@DeleteMapping("/{id}")
    public int deleteStudent(@PathVariable Long id){
    return studentRepository.deleteById(id);
}
}

package com.spring.onetoone.service;


import com.spring.onetoone.controller.StudentController;
import com.spring.onetoone.model.Student;
import com.spring.onetoone.repository.StudentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;


public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.getAll();
    }

    public Student findStudentById(@PathVariable Long id) {
        logger.info("getting data from student database");
        return studentRepository.findById(id);
    }

    public int addStudent(@RequestBody Student student) {
        return studentRepository.addStudent(student);
    }

    public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
        return studentRepository.updateStudent(id, student);
    }

    public int deleteStudent(@PathVariable Long id) {
        return studentRepository.deleteById(id);
    }
}

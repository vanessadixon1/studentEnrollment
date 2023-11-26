package com.amcsoftware.student.controller;

import com.amcsoftware.student.model.Student;
import com.amcsoftware.student.service.StudentServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
public class StudentController {
    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("/users")
    public List<Student> getAllStudents() {
        return studentServices.getStudents();
    }

    @GetMapping()
    public String name() {
        return "Vanessa";
    }

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable(name = "id") UUID id) {
        return studentServices.getStudentById(id);
    }


}

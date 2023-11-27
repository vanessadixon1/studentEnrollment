package com.amcsoftware.student.controller;

import com.amcsoftware.student.model.Student;
import com.amcsoftware.student.model.records.StudentRegistrationRequest;
import com.amcsoftware.student.model.records.StudentUpdateRequest;
import com.amcsoftware.student.service.StudentServices;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class StudentController {
    private final StudentServices studentServices;

    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("/students")
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

    @PostMapping("/add")
    public void addStudent(@RequestBody StudentRegistrationRequest request) {
        studentServices.addStudent(request);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStudent(@PathVariable(name = "id") UUID id) {
        studentServices.deleteStudent(id);
    }

    @PutMapping("/update/{id}")
    public void updateStudent(@PathVariable(name = "id") UUID id, @RequestBody StudentUpdateRequest request) {
        studentServices.updateStudent(id,request);
    }


}

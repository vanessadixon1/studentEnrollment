package com.amcsoftware.student.controller;

import com.amcsoftware.jwt.JWTUtil;
import com.amcsoftware.student.model.Student;
import com.amcsoftware.student.model.records.StudentRegistrationRequest;
import com.amcsoftware.student.model.records.StudentUpdateRequest;
import com.amcsoftware.student.service.StudentDTO;
import com.amcsoftware.student.service.StudentServices;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class StudentController {
    private final StudentServices studentServices;
    private final JWTUtil jwtUtil;

    public StudentController(StudentServices studentServices, JWTUtil jwtUtil) {
        this.studentServices = studentServices;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/students")
    public List<StudentDTO> getAllStudents() {
        return studentServices.getStudents();
    }

    @GetMapping()
    public List<StudentDTO> name() {
        return studentServices.getStudents();
    }

    @GetMapping("/{id}")
    public StudentDTO getStudent(@PathVariable(name = "id") UUID id) {
        return studentServices.getStudentById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addStudent(@RequestBody StudentRegistrationRequest request) {
        studentServices.addStudent(request);
        String jwToken = jwtUtil.issueToken(request.email(),"ROLE_USER");
        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwToken).build();
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

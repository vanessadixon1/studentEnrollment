package com.amcsoftware.student.service;

import com.amcsoftware.exceptions.uncheckedExceptions.ResourceNotFound;
import com.amcsoftware.student.dao.StudentDao;
import com.amcsoftware.student.model.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

//this class is responsible for throwing the exception
@Service
public class StudentServices {
    private final StudentDao studentDao;

    public StudentServices(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getStudents() {
        return studentDao.selectAllStudents();
    }

    public Student getStudentById(UUID id) {
        return studentDao.findStudentById(id).orElseThrow(() -> new ResourceNotFound("student not found with id [%s]".formatted(id)));
    }
}

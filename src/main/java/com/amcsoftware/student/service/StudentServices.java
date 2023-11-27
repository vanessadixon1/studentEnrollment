package com.amcsoftware.student.service;

import com.amcsoftware.exceptions.uncheckedExceptions.DuplicateRequest;
import com.amcsoftware.exceptions.uncheckedExceptions.RequestValidationException;
import com.amcsoftware.exceptions.uncheckedExceptions.ResourceNotFound;
import com.amcsoftware.student.dao.StudentDao;
import com.amcsoftware.student.model.Student;
import com.amcsoftware.student.model.records.StudentRegistrationRequest;
import com.amcsoftware.student.model.records.StudentUpdateRequest;
import com.sun.jdi.request.DuplicateRequestException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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
        return studentDao.findStudentById(id).orElseThrow(() -> new ResourceNotFound("student not found with id %s".formatted(id)));
    }

    public void addStudent(StudentRegistrationRequest studentRegistrationRequest) {
        checkEmailAvailability(studentRegistrationRequest.email());
        Student newStudent = new Student(studentRegistrationRequest.firstName(),
                studentRegistrationRequest.lastName(),studentRegistrationRequest.email(),
                studentRegistrationRequest.phoneNumber(),studentRegistrationRequest.age());
        studentDao.insertStudent(newStudent);
    }

    public void deleteStudent(UUID id) {
        if(!studentDao.existById(id)) {
            throw new ResourceNotFound("%s doesn't exist".formatted(id));
        }
        studentDao.deleteStudentWithId(id);
    }

    public void updateStudent(UUID id, StudentUpdateRequest request) {
        Student student = getStudentById(id);

        boolean isChanged = false;

        if(request.firstName() != null && !student.getFistName().equals(request.firstName())) {
            student.setFistName(request.firstName());
            isChanged = true;
        }

        if(request.lastName() != null && !student.getLastName().equals(request.lastName())) {
            student.setLastName(request.lastName());
            isChanged = true;
        }

        if(request.email() != null && !student.getEmail().equals(request.email())) {
            checkEmailAvailability(request.email());
            student.setEmail(request.email());
            isChanged = true;
        }

        if(request.phoneNumber() != null && !student.getPhoneNumber().equals(request.phoneNumber())) {
            student.setPhoneNumber(request.phoneNumber());
            isChanged = true;
        }

        if(!isChanged) {
            throw new RequestValidationException("no changes made");
        }
        studentDao.updateStudentWithId(student);
    }

    private void checkEmailAvailability(String email) {
        if(studentDao.isEmail(email)) {
            throw new DuplicateRequest("%s already taken".formatted(email));
        }
    }
}

package com.amcsoftware.student.service;

import com.amcsoftware.student.dao.StudentDao;
import com.amcsoftware.student.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class StudentDataAccessService implements StudentDao {
    private final StudentRepository studentRepository;

    public StudentDataAccessService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> selectAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Optional<Student> findStudentById(UUID id) {
        return studentRepository.findById(id);
    }
}

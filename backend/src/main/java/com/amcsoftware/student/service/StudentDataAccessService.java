package com.amcsoftware.student.service;

import com.amcsoftware.student.dao.StudentDao;
import com.amcsoftware.student.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("jpa")
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

    @Override
    public void insertStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public boolean isEmail(String email) {
        return studentRepository.existsStudentByEmail(email);
    }

    @Override
    public boolean existById(UUID id) {
        return studentRepository.existsById(id);
    }

    @Override
    public void deleteStudentWithId(UUID id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateStudentWithId(Student student) {
        studentRepository.save(student);
    }
}

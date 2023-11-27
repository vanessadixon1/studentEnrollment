package com.amcsoftware.student.dao;

import com.amcsoftware.student.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudentDao {
     List<Student> selectAllStudents();
     Optional<Student> findStudentById(UUID id);
     void insertStudent(Student student);
     boolean isEmail(String email);
     boolean existById(UUID id);
     void deleteStudentWithId(UUID id);
     void updateStudentWithId(Student student);
}

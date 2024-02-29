package com.amcsoftware.student.service;

import com.amcsoftware.rowMappers.StudentRowMapper;
import com.amcsoftware.student.dao.StudentDao;
import com.amcsoftware.student.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository("jdbc")

public class StudentJDBCAccessService implements StudentDao {

    private final JdbcTemplate jdbcTemplate;
    private final StudentRowMapper studentRowMapper;

    public StudentJDBCAccessService(JdbcTemplate jdbcTemplate, StudentRowMapper studentRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.studentRowMapper = studentRowMapper;
    }

    @Override
    public List<Student> selectAllStudents() {
        var sql = """
                SELECT id, first_name, last_name, email, phone_number, age, gender from Student
                """;

        return jdbcTemplate.query(sql, studentRowMapper);
    }

    @Override
    public Optional<Student> findStudentById(UUID id) {
        var sql = """
                SELECT * FROM student WHERE id = ?
                """;

        return jdbcTemplate.query(sql,studentRowMapper,id).stream().findFirst();
    }

    @Override
    public void insertStudent(Student student) {
        var sql = """
                INSERT INTO Student (id, first_name, last_name, email, phone_number, age, gender) VALUES(?,?,?,?,?,?,?)
                """;
        int result = jdbcTemplate.update(sql, UUID.randomUUID(), student.getFirstName(), student.getLastName(),
                student.getEmail(), student.getPhoneNumber(), student.getAge(), student.getGender().name());
        System.out.println("Updated = " + result);
    }

    @Override
    public boolean isEmail(String email) {
        var sql = """
                SELECT * FROM Student WHERE email = ?
                """;
        return !jdbcTemplate.query(sql,studentRowMapper, email).isEmpty();
    }

    @Override
    public boolean existById(UUID id) {
        var sql = """
                SELECT * FROM Student WHERE id = ?
                """;
        System.out.println(!jdbcTemplate.query(sql, studentRowMapper, id).isEmpty());

        return !jdbcTemplate.query(sql, studentRowMapper, id).isEmpty();
    }

    @Override
    public void deleteStudentWithId(UUID id) {
        var sql = """
                DELETE FROM Student WHERE id = ?
                """;
        jdbcTemplate.update(sql,id);
    }

    @Override
    public void updateStudentWithId(Student student) {
        String sql = """
                    UPDATE student SET first_name = ?, last_name = ?, phone_number = ?, email = ?
                    WHERE id = ?
                    """;
        jdbcTemplate.update(sql, student.getFirstName(), student.getLastName(), student.getPhoneNumber(), student.getEmail(), student.getId());
    }
}

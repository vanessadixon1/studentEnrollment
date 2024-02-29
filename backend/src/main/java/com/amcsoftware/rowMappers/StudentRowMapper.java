package com.amcsoftware.rowMappers;

import com.amcsoftware.student.model.Gender;
import com.amcsoftware.student.model.Student;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Component
public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Student(
                (UUID) rs.getObject("id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("phone_number"),
                rs.getInt("age"),
                Gender.valueOf(rs.getString("gender"))
        );
    }
}

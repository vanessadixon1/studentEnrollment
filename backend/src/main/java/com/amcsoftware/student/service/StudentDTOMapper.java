package com.amcsoftware.student.service;

import com.amcsoftware.student.model.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.stream.Collectors;


@Service
public class StudentDTOMapper implements Function<Student,StudentDTO> {
    @Override
    public StudentDTO apply(Student student) {
        return  new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail(),
                student.getGender(),
                student.getPhoneNumber(),
                student.getAge(),
                student.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()),
                student.getUsername());
    }
}

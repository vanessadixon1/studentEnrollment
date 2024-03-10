package com.amcsoftware.student.service;

import com.amcsoftware.student.model.Gender;

import java.util.List;
import java.util.UUID;

public record StudentDTO(
        UUID id,
        String firstName,
        String lastName,
        String email,
        Gender gender,
        String phoneNumber,
        Integer age,
        List<String> roles,
        String username) {
}

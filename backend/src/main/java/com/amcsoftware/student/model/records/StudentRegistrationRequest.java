package com.amcsoftware.student.model.records;


import com.amcsoftware.student.model.Gender;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String password,
        String phoneNumber,
        Integer age,
        Gender gender
) {

}


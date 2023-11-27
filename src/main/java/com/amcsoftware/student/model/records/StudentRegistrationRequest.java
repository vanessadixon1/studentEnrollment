package com.amcsoftware.student.model.records;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Integer age
) {

}

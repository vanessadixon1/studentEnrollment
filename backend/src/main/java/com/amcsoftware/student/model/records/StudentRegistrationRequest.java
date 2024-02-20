package com.amcsoftware.student.model.records;

import java.util.ArrayList;

public record StudentRegistrationRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        Integer age
) {

}


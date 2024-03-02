package com.amcsoftware.student.model.records;

public record StudentUpdateRequest(
        String firstName,
        String lastName,
        String email,
        String phoneNumber
) {
}

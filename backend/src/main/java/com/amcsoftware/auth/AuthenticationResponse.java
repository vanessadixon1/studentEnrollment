package com.amcsoftware.auth;

import com.amcsoftware.student.service.StudentDTO;

public record AuthenticationResponse(
        String token,
        StudentDTO studentDTO) {
}

package com.amcsoftware.auth;

import com.amcsoftware.jwt.JWTUtil;
import com.amcsoftware.student.model.Student;
import com.amcsoftware.student.service.StudentDTO;
import com.amcsoftware.student.service.StudentDTOMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final StudentDTOMapper studentDTOMapper;
    private final JWTUtil jwtUtil;

    public AuthenticationService(AuthenticationManager authenticationManager,
                                 StudentDTOMapper studentDTOMapper,
                                 JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.studentDTOMapper = studentDTOMapper;
        this.jwtUtil = jwtUtil;
    }

    public AuthenticationResponse login(AuthenticationRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.username(),
                        request.password()
                )
        );
        Student principal = (Student) authentication.getPrincipal();
        StudentDTO studentDTO = studentDTOMapper.apply(principal);
        String token = jwtUtil.issueToken(studentDTO.username(), studentDTO.roles());
        return new AuthenticationResponse(token,studentDTO);
    }

}

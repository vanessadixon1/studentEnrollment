package com.amcsoftware.auth;

public record AuthenticationRequest(
        String username,
        String password
) {
}

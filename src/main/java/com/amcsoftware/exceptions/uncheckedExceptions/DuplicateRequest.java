package com.amcsoftware.exceptions.uncheckedExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateRequest extends RuntimeException {

    public DuplicateRequest(String message) {
        super(message);
    }
}

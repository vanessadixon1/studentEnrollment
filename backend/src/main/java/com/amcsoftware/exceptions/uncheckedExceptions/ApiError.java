package com.amcsoftware.exceptions.uncheckedExceptions;

import java.time.LocalDateTime;

public record
ApiError (
    String path,
    String message,
    int statusCode,
    LocalDateTime localDateTime
) {
}

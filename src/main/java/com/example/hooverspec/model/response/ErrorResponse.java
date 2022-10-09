package com.example.hooverspec.model.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.util.UUID;

@Getter
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private UUID ref;
    private HttpStatus httpStatus;
    private String message;

    public ErrorResponse(HttpStatus httpStatus, String message) {
        this.ref = UUID.randomUUID();
        this.httpStatus = httpStatus;
        this.message = message;
    }
}

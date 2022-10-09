package com.example.hooverspec.util;

import com.example.hooverspec.model.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerUtil extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ResourceNotFound.class)
    protected ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFound ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND, "requested resource not found.");
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(status, "you have passed invalid request parameter : " + ex.getParameter().getParameterName());
        return this.handleExceptionInternal(ex, errorResponse, headers, status, request);
    }

    @ExceptionHandler(value = HttpClientErrorException.class)
    protected ResponseEntity<ErrorResponse> handleClientException(HttpClientErrorException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(), "please check your request information.");
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(value = HttpServerErrorException.class)
    protected ResponseEntity<ErrorResponse> handleServerException(HttpClientErrorException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getStatusCode(), "something went wrong.");
        log.error(String.valueOf(ex.getRootCause()));
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }

    @ExceptionHandler(value = RuntimeException.class)
    protected ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong.");
        log.error(String.valueOf(ex.getCause()));
        return new ResponseEntity<>(errorResponse, errorResponse.getHttpStatus());
    }
}

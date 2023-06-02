package ua.malysh.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import ua.malysh.service.exceptions.ProfileAlreadyExistsException;
import ua.malysh.service.exceptions.ProfileNotFoundException;

@ControllerAdvice
public class UserProfileExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = { ProfileNotFoundException.class })
    protected ResponseEntity<Object> handleNotFound(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = { ProfileAlreadyExistsException.class })
    protected ResponseEntity<Object> handleAlreadyExists(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getMessage(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}

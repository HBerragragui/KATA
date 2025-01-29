package com.alten.backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        String msg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }
    @ExceptionHandler(NotAuthorizedException.class)
    public ResponseEntity<String> handleNotAuthorizedException(NotAuthorizedException ex) {
        String msg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<String> handleUsernameNotFoundException(UsernameNotFoundException ex) {
        String msg = ex.getMessage();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(msg);
    }
}

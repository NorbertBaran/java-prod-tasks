package uj.jwzp2019.hellospring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
class IllegalArgumentHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> interceptIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

package com.radchenko.arhat.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler (value = {NotFoundEntityException.class})
    public ResponseEntity<Object> handleNotFoundEntity(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {BrokenRequestException.class})
    public ResponseEntity<Object> handleBrokenRequestData(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {UserNotRegisteredException.class})
    public ResponseEntity<Object> handleUserNotRegisteredException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
//    public ResponseEntity<Object> handleRequestValidationException(MethodArgumentNotValidException e) {
//        //TODO: should catch and make some other Object/String as a result of failing?
//        return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
//    }
}

package com.exercise.irrigation.exceptions.handlers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.exercise.irrigation.exceptions.AlreadyExistException;
import com.exercise.irrigation.exceptions.NotFoundException;
import com.exercise.irrigation.response.ResponseHandler;
import jakarta.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GeneralExceptionHandler {


    @ExceptionHandler(AlreadyExistException.class)
    public ResponseEntity<Object> handleAlreadyExistException(AlreadyExistException ex, HttpServletRequest req){
        String message = String.format("%s", ex.getMessage());
        return ResponseHandler.generateResponse(message, HttpStatus.valueOf(409), null,false);

    }


    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException ex, HttpServletRequest req){
        String message = String.format("%s", ex.getMessage());
        return ResponseHandler.generateResponse(message, HttpStatus.valueOf(405), null,false);

    }
    
}

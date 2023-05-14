package com.exercise.irrigation.exceptions;

import lombok.Getter;

public class AlreadyExistException extends RuntimeException{

    @Getter
    private final String message;

    public AlreadyExistException(String message){
        super();
        this.message=message;
    }
    
}

package com.exercise.irrigation.exceptions;

import lombok.Getter;

public class NotFoundException extends RuntimeException{

    @Getter
    private final String message;

    public NotFoundException(String message){
        super();
        this.message=message;
    }
}

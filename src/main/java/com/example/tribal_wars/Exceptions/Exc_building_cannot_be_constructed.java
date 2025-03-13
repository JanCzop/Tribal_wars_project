package com.example.tribal_wars.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class Exc_building_cannot_be_constructed extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Building cannot be built due to: ";
    public Exc_building_cannot_be_constructed(String message){
        super(DEFAULT_MESSAGE + message);
    }
}

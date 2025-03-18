package com.example.tribal_wars.backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class Exc_invalid_request extends RuntimeException{
    public Exc_invalid_request(String message){
        super(message);
    }
}

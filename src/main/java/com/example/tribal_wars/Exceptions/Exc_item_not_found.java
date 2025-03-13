package com.example.tribal_wars.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class Exc_item_not_found extends RuntimeException{
    public Exc_item_not_found(String message){
        super(message);
    }
}

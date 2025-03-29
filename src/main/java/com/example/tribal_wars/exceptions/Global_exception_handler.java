package com.example.tribal_wars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class Global_exception_handler {
    @ExceptionHandler(Exc_item_not_found.class)
    public ResponseEntity<String> handle_item_not_found(Exc_item_not_found exc){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exc.getMessage());
    }
    @ExceptionHandler(Exc_invalid_request.class)
    public ResponseEntity<String> handle_item_not_found(Exc_invalid_request exc){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }
    @ExceptionHandler(Exc_building_cannot_be_constructed.class)
    public ResponseEntity<String> handle_building_cannot_be_constructed(Exc_building_cannot_be_constructed exc){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exc.getMessage());
    }
}

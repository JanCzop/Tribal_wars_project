package com.example.tribal_wars.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(Exc_not_enough_resources.class)
    public ResponseEntity<String> handle_not_enough_resources(Exc_not_enough_resources exc){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exc.getMessage());
    }
    @ExceptionHandler(Exc_recruitment_queue_full.class)
    public ResponseEntity<String> handleExcRecruitmentQueueFull(Exc_recruitment_queue_full ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}

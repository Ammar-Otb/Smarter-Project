package com.example.capstoneproject.advicecontroller;

import com.example.capstoneproject.DTO.API;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class AdviceHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity noSuchElementException(NoSuchElementException e){
        return ResponseEntity.status(400).body(new API(e.getMessage(), 400));
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity dataIntegrityViolationException(DataIntegrityViolationException e){
        return ResponseEntity.status(400).body(new API(e.getRootCause().getMessage(), 400));
    }
    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity invalidDataAccessApiUsageException(InvalidDataAccessApiUsageException e){
        return ResponseEntity.status(400).body(new API(e.getRootCause().getMessage(), 400));
    }

}

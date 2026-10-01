package com.example.librasys.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidation(
            MethodArgumentNotValidException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        erros.put(
                                error.getField(),
                                error.getDefaultMessage()
                        )
                );

        return ResponseEntity.badRequest().body(erros);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> erros = new HashMap<>();

        ex.getConstraintViolations()
                .forEach(error -> {

                    String campo =
                            error.getPropertyPath().toString();

                    if (campo.contains(".")) {
                        campo =
                                campo.substring(
                                        campo.lastIndexOf(".") + 1
                                );
                    }

                    erros.put(
                            campo,
                            error.getMessage()
                    );
                });

        return ResponseEntity
                .badRequest()
                .body(erros);
    }
}
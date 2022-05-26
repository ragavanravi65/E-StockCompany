package com.company.service.eStockCompany.util;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class InvalidCompanyInputHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleInvalidInputResponse(MethodArgumentNotValidException exception){
        Map<String,String> errorStats=new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(
                error-> errorStats.put(error.getField(),error.getDefaultMessage())
        );
        return ResponseEntity.badRequest().body(errorStats);
    }
}

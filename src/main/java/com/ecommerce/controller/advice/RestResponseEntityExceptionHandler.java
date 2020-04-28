package com.ecommerce.controller.advice;

import com.ecommerce.exception.BusinessException;
import com.ecommerce.exception.UnauthorizedException;
import com.ecommerce.model.response.ResponseModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;


@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UnauthorizedException.class)
    protected ResponseEntity<Object> handleConflict(UnauthorizedException ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel("401", ex.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<Object> handelBusinessException(RuntimeException ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel("422", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleConflict(Exception ex, WebRequest request) {
        log.error(ex.getMessage());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel("500", ex.getMessage()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errorMessages = new ArrayList<>();
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            errorMessages.add(error.getDefaultMessage());
        }
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseModel("400", "Validation Fail", errorMessages));
    }

}


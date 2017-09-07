package com.mediaocean.retail.store.exception;

import com.mediaocean.retail.store.dto.Exception;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {

    private final static String ILLEGAL_ARG_EXCEPTION = "1001";
    private final static String RUNTIME_EXCEPTION = "1000";

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Exception> illegalArgumentExceptionHandler(IllegalArgumentException ex) {
        log.error("Illegal Arguement Exception During processing request",ex);
        Exception response = new Exception();
        response.setErrorCode(ILLEGAL_ARG_EXCEPTION);
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<Exception>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Exception> runtimeExceptionHandler(RuntimeException ex) {

        log.error("Runtime Exception During processing request",ex);
        Exception response = new Exception();
        response.setErrorCode(RUNTIME_EXCEPTION);
        response.setErrorMessage(ex.getMessage());

        return new ResponseEntity<Exception>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

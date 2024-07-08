package com.customer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<?> handleSQLException(final HttpServletRequest request, Exception ex) {
        LOGGER.info("SQL Exception Occurred!!! url={}", request.getRequestURL());
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(ex.getMessage());
    }

    @ExceptionHandler({CustomerIdException.class})
    public ResponseEntity<?> handleCustomerNotFound(final HttpServletRequest request, CustomerIdException cEx) {
        LOGGER.info("Customer Exception Occurred!!! url={}", request.getRequestURL());
        return ResponseEntity.status(NOT_FOUND)
                .body(cEx.getMessage());
    }
}

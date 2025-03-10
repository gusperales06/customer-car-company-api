package com.customer.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class GlobalExceptionHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<?> handleSQLException(final HttpServletRequest request, Exception ex) {
        LOGGER.info("SQL Exception Occurred!!! url={}", request.getRequestURL());
        return ResponseEntity.internalServerError()
                .body(ex.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<?> handleArgumentValidException(final HttpServletRequest request, MethodArgumentNotValidException manvEx) {
        LOGGER.info("Bean Validation Exception Occurred!!! url={}", request.getRequestURL());
        var body = new HashMap<>();
        var errors = manvEx.getBindingResult() //
                .getFieldErrors()
                .stream() //
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("errors", errors);
        return ResponseEntity.badRequest()
                .body(body);
    }

    @ExceptionHandler({CustomerIdException.class})
    public ResponseEntity<?> handleCustomerNotFoundException(final HttpServletRequest request, CustomerIdException cEx) {
        LOGGER.info("Customer Exception Occurred!!! url={}", request.getRequestURL());
        return ResponseEntity.status(NOT_FOUND)
                .body(cEx.getMessage());
    }

    @ExceptionHandler({NonCustomerChangesException.class})
    public ResponseEntity<?> handleNonCustomerChangesException(final HttpServletRequest request, NonCustomerChangesException nccEx) {
        LOGGER.info("Customer Update Exception Occurred!!! url={}", request.getRequestURL());
        return ResponseEntity.badRequest()
                .body(nccEx.getMessage());
    }

    @ExceptionHandler({EmptyResultDataAccessException.class})
    public ResponseEntity<?> handleEmptyResultDataAccessException(final HttpServletRequest request, EmptyResultDataAccessException erdaEx) {
        LOGGER.info("Data Access Exception Occurred!!! url={}", request.getRequestURL());
        return ResponseEntity.badRequest()
                .body(erdaEx.getMessage());
    }
}

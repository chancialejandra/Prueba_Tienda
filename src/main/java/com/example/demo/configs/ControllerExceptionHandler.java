package com.example.demo.configs;

import com.example.demo.exceptions.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

  @ExceptionHandler(Exception.class)
  protected ResponseEntity<ApiError> handleUnknownException(Exception e) {

    ApiError apiError = ApiError.builder()
        .error("internal_error")
        .message("Internal server error")
        .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).build();

    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }

  @ExceptionHandler(HttpMessageNotReadableException.class)
  public ResponseEntity<ApiError> malformedJsonException(HttpMessageNotReadableException exc) {

    ApiError apiError = ApiError.builder()
        .error("Json is malformed")
        .message("Json is malformed")
        .status(HttpStatus.BAD_REQUEST.value())
        .build();

    return ResponseEntity.status(apiError.getStatus()).body(apiError);

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ApiError> malformedBodyException(MethodArgumentNotValidException e) {

    ApiError apiError = ApiError.builder()
        .error("Input is invalid")
        .message(e.getFieldError().getDefaultMessage())
        .status(HttpStatus.BAD_REQUEST.value())
        .build();

    return ResponseEntity.status(apiError.getStatus()).body(apiError);

  }

  @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ApiError> methodNotSupportedException(HttpRequestMethodNotSupportedException e) {
    ApiError apiError = ApiError.builder()
        .error("Method Not Supported")
        .message(e.getMessage())
        .status(HttpStatus.METHOD_NOT_ALLOWED.value())
        .build();

    return ResponseEntity.status(apiError.getStatus()).body(apiError);
  }
}

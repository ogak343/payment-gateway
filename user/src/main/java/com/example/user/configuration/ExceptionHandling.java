package com.example.user.configuration;

import com.example.user.helper.ApplicationException;
import feign.FeignException;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandling {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exp) {
        var errors = new HashMap<String, String>();
        exp.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var fieldName = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return ResponseEntity
                .status(exp.getStatusCode())
                .body(new ValidationError(errors));
    }

    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ProblemDetail> handleFeignException(FeignException ex) {
        HttpStatus status = HttpStatus.valueOf(ex.status());
        String message = ex.contentUTF8();
        ProblemDetail errorResponse = ProblemDetail.forStatusAndDetail(status, message);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ProblemDetail> handleApplicationException(ApplicationException ex) {
        ProblemDetail detail = ex.getBody();
        return new ResponseEntity<>(detail, ex.getStatusCode());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> unhandledError(RuntimeException exp) {
        return ResponseEntity.status(500).body(new ExceptionResponse(500, "Internal Server Error"));
    }

    public record ValidationError(Map<String, String> errors) {

    }

    @Getter
    public static class ExceptionResponse extends RuntimeException {

        private int status;
        private String body;

        public ExceptionResponse(int status, String body) {
            this.status = status;
            this.body = body;
        }
    }
}

package com.banking.teamone.handler;

import com.banking.teamone.exception.DatabaseIntegrityException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@ControllerAdvice
public class DatabaseIntegrityExceptionHandler {

    static class Error {
        private final int status;
        private final List<String> message;

        Error(int status, List<String> message) {
            this.status = status;
            this.message = message;
        }

        public int getStatus() {
            return status;
        }

        public List<String> getMessage() {
            return message;
        }

    }
    @ExceptionHandler(value = DatabaseIntegrityException.class)
    public ResponseEntity handleDatabaseIntegrityException(DatabaseIntegrityException exception) {
        return new ResponseEntity<>(exception.getMessage(), BAD_REQUEST);
    }

    @ResponseStatus(BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        return processFieldErrors(fieldErrors);
    }
    private Error processFieldErrors(List<org.springframework.validation.FieldError> fieldErrors) {
        List<String> errorList = new ArrayList<String>();
        for (FieldError fieldError: fieldErrors) {
            String errorString = String.format("%s %s", fieldError.getField(), fieldError.getDefaultMessage());
            errorList.add(errorString);
        }
        return new Error(BAD_REQUEST.value(), errorList);
    }
}

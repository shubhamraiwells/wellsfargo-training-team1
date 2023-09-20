package com.banking.teamone.handler;

import com.banking.teamone.exception.DatabaseExceptions;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.banking.teamone.handler.Error.processFieldErrors;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@ControllerAdvice
public class DatabaseExceptionsHandler {

    @ExceptionHandler(value = DatabaseExceptions.AadharCardRegisteredException.class)
    public ResponseEntity handleDatabaseIntegrityException(DatabaseExceptions.AadharCardRegisteredException exception) {
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
}

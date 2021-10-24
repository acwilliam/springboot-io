package com.acwilliam.springboot.handler;

import com.acwilliam.springboot.exception.BadRequestException;
import com.acwilliam.springboot.exception.BadRequestExceptionDetails;
import com.acwilliam.springboot.exception.validationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Log4j2
public class ResquestExpetionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException bre){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad request Exception, Check the documentation")
                        .detalhes(bre.getMessage())
                        .mensagemDesenvolvedor(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<validationExceptionDetails> MethodArgumentNotValidException
            (MethodArgumentNotValidException exception){
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));
         return new ResponseEntity<>(
                 validationExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad request Exception, campo invalido")
                        .detalhes("Verifique os campos de erro")
                        .mensagemDesenvolvedor(exception.getClass().getName())
                        .fields(fields)
                         .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }


}

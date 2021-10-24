package com.acwilliam.springboot.handler;

import com.acwilliam.springboot.exception.BadRequestException;
import com.acwilliam.springboot.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ResquestExpetionHandler {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timeStamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .titulo("Bad request Exception, Check the documentation")
                        .detalhes(badRequestException.getMessage())
                        .mensagemDesenvolvedor(badRequestException.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}

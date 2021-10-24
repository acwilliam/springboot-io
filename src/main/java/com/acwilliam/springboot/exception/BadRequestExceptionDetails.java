package com.acwilliam.springboot.exception;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String titulo;
    private int status;
    private String detalhes;
    private String mensagemDesenvolvedor;
    private LocalDateTime timeStamp;
}


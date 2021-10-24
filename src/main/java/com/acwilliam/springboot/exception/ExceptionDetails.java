package com.acwilliam.springboot.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
public class ExceptionDetails {
    protected String titulo;
    protected int status;
    protected String detalhes;
    protected String mensagemDesenvolvedor;
    protected LocalDateTime timeStamp;
}

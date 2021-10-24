package com.acwilliam.springboot.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class validationExceptionDetails extends ExceptionDetails {
    private final String fields;
    private final String fieldsMessage;
}

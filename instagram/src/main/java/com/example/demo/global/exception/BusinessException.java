package com.example.demo.global.exception;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.global.result.ErrorResponse;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private ErrorCode errorCode;
    private List<ErrorResponse.FieldError> errors = new ArrayList<>();

    public BusinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}

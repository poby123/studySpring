package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class EntityAlreadyExistException extends BusinessException {

    public EntityAlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}

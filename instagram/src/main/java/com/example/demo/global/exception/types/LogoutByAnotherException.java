package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class LogoutByAnotherException extends BusinessException{

    public LogoutByAnotherException() {
        super(ErrorCode.LOGOUT_BY_ANOTHER);
    }
    
}

package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class UsernameDuplicateException extends BusinessException{

    public UsernameDuplicateException() {
        super(ErrorCode.USERNAME_ALREADY_EXIST);
    }
    
}

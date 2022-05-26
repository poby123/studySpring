package com.example.demo.exception.types;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;

public class UsernameDuplicateException extends BusinessException{

    public UsernameDuplicateException() {
        super(ErrorCode.USERNAME_ALREADY_EXIST);
    }
    
}

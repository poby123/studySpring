package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class BoardNotFoundException extends BusinessException{

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
    
}

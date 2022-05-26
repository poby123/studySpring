package com.example.demo.exception.types;

import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorCode;

public class BoardNotFoundException extends BusinessException{

    public BoardNotFoundException() {
        super(ErrorCode.BOARD_NOT_FOUND);
    }
    
}

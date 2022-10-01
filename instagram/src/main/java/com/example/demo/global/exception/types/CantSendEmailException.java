package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class CantSendEmailException extends BusinessException {
    public CantSendEmailException() {
		super(ErrorCode.CANT_SEND_EMAIL);
	}
}

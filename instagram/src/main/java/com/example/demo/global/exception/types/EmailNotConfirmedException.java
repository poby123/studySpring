package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class EmailNotConfirmedException extends BusinessException {
	public EmailNotConfirmedException() {
		super(ErrorCode.EMAIL_NOT_CONFIRMED);
	}

}

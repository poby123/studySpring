package com.example.demo.domain.member.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class PasswordResetFailException extends BusinessException {
	public PasswordResetFailException() {
		super(ErrorCode.PASSWORD_RESET_FAIL);
	}

}

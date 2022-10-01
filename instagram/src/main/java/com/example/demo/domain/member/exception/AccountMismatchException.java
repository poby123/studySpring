package com.example.demo.domain.member.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class AccountMismatchException extends BusinessException {
	public AccountMismatchException() {
		super(ErrorCode.ACCOUNT_MISMATCH);
	}

}

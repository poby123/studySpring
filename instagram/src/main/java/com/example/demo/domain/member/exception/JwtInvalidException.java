package com.example.demo.domain.member.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class JwtInvalidException extends BusinessException {
	public JwtInvalidException() {
		super(ErrorCode.INVALID_JWT);
	}

}

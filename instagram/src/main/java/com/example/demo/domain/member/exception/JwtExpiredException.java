package com.example.demo.domain.member.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class JwtExpiredException extends BusinessException {
	public JwtExpiredException() {
		super(ErrorCode.EXPIRED_ACCESS_TOKEN);
	}

}

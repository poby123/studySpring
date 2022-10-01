package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class EntityNotFoundException extends BusinessException {

	public EntityNotFoundException(ErrorCode errorCode) {
		super(errorCode);
	}
}
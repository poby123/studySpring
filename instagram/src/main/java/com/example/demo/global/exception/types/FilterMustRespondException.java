package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class FilterMustRespondException extends BusinessException {

	public FilterMustRespondException() {
		super(ErrorCode.FILTER_MUST_RESPOND);
	}

}

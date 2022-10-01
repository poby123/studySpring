package com.example.demo.global.exception.types;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class FileConvertFailException extends BusinessException {

	public FileConvertFailException() {
		super(ErrorCode.FILE_CONVERT_FAIL);
	}

}
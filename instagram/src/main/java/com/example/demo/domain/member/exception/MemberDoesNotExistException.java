package com.example.demo.domain.member.exception;

import com.example.demo.global.exception.BusinessException;
import com.example.demo.global.exception.ErrorCode;

public class MemberDoesNotExistException extends BusinessException {
    public MemberDoesNotExistException() {
        super(ErrorCode.MEMBER_NOT_FOUND);
    }
}

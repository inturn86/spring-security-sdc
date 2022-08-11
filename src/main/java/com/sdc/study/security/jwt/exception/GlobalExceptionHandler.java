package com.sdc.study.security.jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sdc.study.core.CommonResponse;
import com.sdc.study.security.define.EErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(JwtRuntimeException.class)
//    @ResponseBody
    protected ResponseEntity<CommonResponse> handleJwtRuntimeException(JwtRuntimeException e) {

        log.info("handleCustomAuthenticationException", e);

        CommonResponse response = CommonResponse.builder()
                .code(EErrorCode.AUTHENTICATION_FAILED.getCode())
                .message(e.getMessage())
                .status(EErrorCode.AUTHENTICATION_FAILED.getStatus())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

}

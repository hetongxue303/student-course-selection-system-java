package com.hetongxue.handler;

import com.hetongxue.base.enums.ResponseCode;
import com.hetongxue.base.response.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常捕获
 *
 * @author 何同学
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    public Result nullPointerException(NullPointerException e) {
        log.error(ResponseCode.NULL_POINTER.getMessage());
        e.printStackTrace();
        return Result.Error().setMessage(ResponseCode.NULL_POINTER.getMessage()).setCode(ResponseCode.NULL_POINTER.getCode());
    }

}
package com.hetongxue.handler;

import com.hetongxue.base.enums.ResponseCode;
import com.hetongxue.base.response.Result;
import com.hetongxue.handler.exception.DatabaseDeleteException;
import com.hetongxue.handler.exception.DatabaseInsertException;
import com.hetongxue.handler.exception.DatabaseQueryException;
import com.hetongxue.handler.exception.DatabaseUpdateException;
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
    public Result NullPointerException(NullPointerException e) {
        log.error(ResponseCode.NULL_POINTER.getMessage());
        e.printStackTrace();
        return Result.Error().setMessage(ResponseCode.NULL_POINTER.getMessage()).setCode(ResponseCode.NULL_POINTER.getCode());
    }

    @ExceptionHandler(DatabaseInsertException.class)
    public Result DatabaseInsertException(DatabaseInsertException e) {
        log.error(e.getMessage());
        return Result.Error().setMessage(e.getMessage());
    }

    @ExceptionHandler(DatabaseDeleteException.class)
    public Result DatabaseDeleteException(DatabaseDeleteException e) {
        log.error(e.getMessage());
        return Result.Error().setMessage(e.getMessage());
    }

    @ExceptionHandler(DatabaseUpdateException.class)
    public Result DatabaseUpdateException(DatabaseUpdateException e) {
        log.error(e.getMessage());
        return Result.Error().setMessage(e.getMessage());
    }

    @ExceptionHandler(DatabaseQueryException.class)
    public Result DatabaseQueryException(DatabaseQueryException e) {
        log.error(e.getMessage());
        return Result.Error().setMessage(e.getMessage());
    }

}
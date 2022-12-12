package com.hetongxue.handler.exception;

/**
 * 数据库查询异常
 *
 * @author 何同学
 */
public class DatabaseQueryException extends RuntimeException {

    public DatabaseQueryException() {
    }

    public DatabaseQueryException(String message) {
        super(message);
    }

    public DatabaseQueryException(String message, Throwable cause) {
        super(message, cause);
    }

}
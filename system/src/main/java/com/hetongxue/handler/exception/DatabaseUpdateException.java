package com.hetongxue.handler.exception;

/**
 * 数据库更新异常
 *
 * @author 何同学
 */
public class DatabaseUpdateException extends RuntimeException {

    public DatabaseUpdateException() {
    }

    public DatabaseUpdateException(String message) {
        super(message);
    }

    public DatabaseUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

}
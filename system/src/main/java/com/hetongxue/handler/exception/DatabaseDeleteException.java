package com.hetongxue.handler.exception;

/**
 * 数据库删除异常
 *
 * @author 何同学
 */
public class DatabaseDeleteException extends RuntimeException {

    public DatabaseDeleteException() {
    }

    public DatabaseDeleteException(String message) {
        super(message);
    }

    public DatabaseDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

}
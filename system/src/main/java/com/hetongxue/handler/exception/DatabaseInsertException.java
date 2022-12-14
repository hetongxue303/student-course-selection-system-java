package com.hetongxue.handler.exception;

/**
 * 数据库插入异常
 *
 * @author 何同学
 */
public class DatabaseInsertException extends RuntimeException {

    public DatabaseInsertException() {
    }

    public DatabaseInsertException(String message) {
        super(message);
    }

    public DatabaseInsertException(String message, Throwable cause) {
        super(message, cause);
    }

}
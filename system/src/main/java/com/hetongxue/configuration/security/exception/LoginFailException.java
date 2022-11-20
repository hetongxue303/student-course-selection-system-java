package com.hetongxue.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 登陆失败异常处理类
 *
 * @author 何同学
 */
public class LoginFailException extends AuthenticationException {

    public LoginFailException(String msg) {
        super(msg);
    }
}
package com.hetongxue.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * JWT异常处理类
 *
 * @author 何同学
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg) {
        super(msg);
    }

}
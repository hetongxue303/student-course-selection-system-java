package com.hetongxue.configuration.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * 验证码异常处理类
 *
 * @author 何同学
 */
public class CaptchaAuthenticationException extends AuthenticationException {

    public CaptchaAuthenticationException(String msg) {
        super(msg);
    }

}
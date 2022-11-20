package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.security.exception.CaptchaAuthenticationException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器
 *
 * @author 何同学
 */
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String message = "登陆失败，未知异常！";
        if (exception instanceof AccountExpiredException) {
            message = "账户过期";
        }
        if (exception instanceof BadCredentialsException) {
            message = "用户名或密码错误";
        }
        if (exception instanceof CredentialsExpiredException) {
            message = "凭据过期";
        }
        if (exception instanceof DisabledException) {
            message = "账户禁用";
        }
        if (exception instanceof LockedException) {
            message = "账户已锁";
        }
        if (exception instanceof InternalAuthenticationServiceException) {
            message = "身份验证异常";
        }
        if (exception instanceof AuthenticationServiceException || exception instanceof UsernameNotFoundException || exception instanceof CaptchaAuthenticationException) {
            message = exception.getMessage();
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Error().setMessage(message)));
    }

}
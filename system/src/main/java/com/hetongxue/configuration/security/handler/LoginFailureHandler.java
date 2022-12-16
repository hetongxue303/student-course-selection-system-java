package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.security.exception.CaptchaAuthenticationException;
import com.hetongxue.configuration.security.exception.JwtAuthenticationException;
import org.springframework.http.HttpStatus;
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
        Result result = Result.Error().setMessage("登陆失败，未知异常！");
        response.setContentType("application/json;charset=utf-8");
        if (exception instanceof AccountExpiredException) {
            result.setMessage("账户过期");
        }
        if (exception instanceof BadCredentialsException) {
            result.setMessage("用户名或密码错误");
        }
        if (exception instanceof CredentialsExpiredException) {
            result.setMessage("凭据过期");
        }
        if (exception instanceof DisabledException) {
            result.setMessage("账户不可用");
        }
        if (exception instanceof LockedException) {
            result.setMessage("账户被锁");
        }
        if (exception instanceof InternalAuthenticationServiceException) {
            result.setMessage("身份验证异常");
        }
        if (exception instanceof JwtAuthenticationException) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            result.setMessage(exception.getMessage()).setCode(HttpStatus.UNAUTHORIZED.value());
        }
        if (exception instanceof AuthenticationServiceException || exception instanceof UsernameNotFoundException || exception instanceof CaptchaAuthenticationException) {
            result.setMessage(exception.getMessage());
        }
        response.getWriter().println(new ObjectMapper().writeValueAsString(result));
    }

}
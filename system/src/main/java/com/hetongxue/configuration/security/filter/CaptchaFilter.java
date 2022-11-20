package com.hetongxue.configuration.security.filter;

import com.hetongxue.base.constant.Base;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.SpringSecurityConfiguration;
import com.hetongxue.configuration.security.exception.CaptchaAuthenticationException;
import com.hetongxue.configuration.security.handler.LoginFailureHandler;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * 验证码过滤器
 *
 * @author 何同学
 */
@Component
public class CaptchaFilter extends OncePerRequestFilter {

    private final static String LOGIN_METHOD = "POST";
    private final static String LOGIN_PATH = SpringSecurityConfiguration.LOGIN_PATH;
    private final static String CAPTCHA_KEY = SpringSecurityConfiguration.CAPTCHA_KEY;

    @Resource
    private LoginFailureHandler loginFailureHandler;
    @Resource
    private RedisUtils redisUtils;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        try {
            // 判断是否为登录URL
            if (!Objects.equals(request.getRequestURI(), LOGIN_PATH)) {
                filterChain.doFilter(request, response);
                return;
            }
            // 判断是否为登录URL和POST请求
            if (Objects.equals(request.getRequestURI(), LOGIN_PATH) && LOGIN_METHOD.equalsIgnoreCase(request.getMethod())) {
                String code = request.getParameter(CAPTCHA_KEY);
                String redisCode = (String) redisUtils.getValue(Base.SECURITY_CAPTCHA);
                if (Objects.isNull(redisCode)) {
                    throw new CaptchaAuthenticationException("验证码过期");
                }
                // 验证码不一致：抛出异常
                if (!Objects.equals(code, redisCode)) {
                    throw new CaptchaAuthenticationException("验证码错误");
                }
                // 验证码一致：删除redis中的验证码并放行
                redisUtils.delete(Base.SECURITY_CAPTCHA);
                filterChain.doFilter(request, response);
            }
        } catch (CaptchaAuthenticationException e) {
            // 删除redis中的验证码并放行
            redisUtils.delete(Base.SECURITY_CAPTCHA);
            // 执行验证失败处理类
            loginFailureHandler.onAuthenticationFailure(request, response, e);
        }
    }

}
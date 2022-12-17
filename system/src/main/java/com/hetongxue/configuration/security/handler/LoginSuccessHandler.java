package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.base.constant.Base;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.entity.UserDetail;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.TokenVO;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 认证成功处理类
 *
 * @author 何同学
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    /**
     * 存储时间
     */
    private static final long TIMEOUT = Base.REDIS_TIMEOUT;
    /**
     * 存储单位
     */
    private static final TimeUnit TIMEUNIT = Base.REDIS_TIMEUNIT;
    /**
     * token key
     */
    private static final String AUTHORIZATION = Base.SECURITY_AUTHORIZATION;

    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;
    @Resource
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        User user = ((UserDetail) authentication.getPrincipal()).getUser();
        String token = jwtUtils.generateToken(user.getUserId(), user.getUsername());
        redisUtils.setValue(AUTHORIZATION, token, TIMEOUT, TIMEUNIT);
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Success(new TokenVO(token, Base.TOKEN_EXPIRATION_TIME, userService.getUserInfo(user))).setMessage("登陆成功")));
    }

}
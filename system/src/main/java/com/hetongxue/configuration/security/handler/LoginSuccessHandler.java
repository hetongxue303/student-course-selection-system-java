package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.base.constant.Base;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.entity.LoginInfo;
import com.hetongxue.system.domain.Account;
import com.hetongxue.system.domain.vo.TokenVo;
import com.hetongxue.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
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

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 设置字符编码
        response.setContentType("application/json;charset=utf-8");
        // 设置状态
        response.setStatus(HttpStatus.OK.value());
        // 获取当前用户信息
        Account account = ((LoginInfo) authentication.getPrincipal()).getAccount();
        // 生成token
        String token = jwtUtils.generateToken(account.getAccountId(), account.getUsername());
        // 将token存于redis中(默认3天)
        redisUtils.setValue(AUTHORIZATION, token, TIMEOUT, TIMEUNIT);
        // 将token设置在请求头上
//        response.setHeader(Base.AUTHORIZATION_KEY, token);
        // 自定义返回内容
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Success(new TokenVo(token, Base.TOKEN_EXPIRATION_TIME)).setMessage("登陆成功")));
    }

}
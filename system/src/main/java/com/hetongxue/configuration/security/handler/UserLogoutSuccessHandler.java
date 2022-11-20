package com.hetongxue.configuration.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hetongxue.base.constant.Base;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 用户注销成功处理类
 *
 * @author 何同学
 */
@Component
public class UserLogoutSuccessHandler implements LogoutSuccessHandler {

    @Resource
    private RedisUtils redisUtils;

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 判断是否有权限信息 有的话删除
        if (!ObjectUtils.isEmpty(authentication)) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        // 设置响应头
        response.setContentType("application/json;charset=utf-8");
        // 设置响应状态
        response.setStatus(HttpStatus.OK.value());
        // 设置响应头token信息为空
        response.setHeader(Base.SECURITY_AUTHORIZATION, "");
        // 清空redis中的token信息
        redisUtils.delete(Base.SECURITY_AUTHORIZATION);
        response.getWriter().println(new ObjectMapper().writeValueAsString(Result.Success().setMessage("注销成功")));
    }

}
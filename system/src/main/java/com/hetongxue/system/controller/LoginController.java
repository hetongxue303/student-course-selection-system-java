package com.hetongxue.system.controller;

import com.hetongxue.base.response.Result;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * 登录模块
 *
 * @author 何同学
 */
@RestController
public class LoginController {

    @Resource
    private AuthenticationProvider authenticationProvider;

    @PostMapping("/login")
    public Result login(String username, String password, String code) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationProvider.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            throw new RuntimeException("用户名或密码错误");
        }
        // TODO 生成token并返回前端
        return null;
    }

}
package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;

/**
 * 用户模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserService userService;

    @GetMapping("/info")
    @LogAnnotation(operate = "获取当前登录用户信息", detail = "获取当前已经登陆用户的路由、菜单及基本信息")
    public Result getUserInfo(HttpServletResponse response) {
        User user = userService.selectOneByUsername(SecurityUtils.getUser().getUsername());
        if (Objects.nonNull(user)) {
            response.setStatus(HttpStatus.OK.value());
            return Result.Success(userService.getUserInfo(user)).setMessage("获取用户信息成功");
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.Error().setMessage("获取用户信息失败").setCode(HttpStatus.UNAUTHORIZED.value());
    }

    @PostMapping("/refreshToken")
    @LogAnnotation(operate = "刷新令牌", detail = "前端过期时间刷新当前令牌")
    public Result refreshToken(HttpServletRequest request) {
        return null;
//        try {
//            String token = request.getHeader(Base.SECURITY_AUTHORIZATION);
//            if (ObjectUtils.isEmpty(token)) {
//                throw new JwtAuthenticationException("token异常");
//            }
//            if (ObjectUtils.isEmpty(jwtUtils.getClaims(token))) {
//                throw new JwtAuthenticationException("token异常");
//            }
//            String newToken = jwtUtils.refreshToken(token);
//            // 删除之前token
//            redisUtils.delete(Base.SECURITY_AUTHORIZATION);
//            // 设置新的token
//            redisUtils.setValue(Base.SECURITY_AUTHORIZATION, newToken, Base.REDIS_TIMEOUT, Base.REDIS_TIMEUNIT);
//            return Result.Success(new TokenVO(newToken, Base.TOKEN_EXPIRATION_TIME)).setMessage("刷新token成功！");
//        } catch (JwtAuthenticationException e) {
//            return Result.Error().setMessage("刷新token失败,请重新登录！");
//        }
    }

    @GetMapping("/get/all/{type}")
    @LogAnnotation(operate = "通过用户类型 获取用户列表 ")
    public Result getUserAll(@PathVariable("type") Integer type) {
        return Result.Success(userService.getUserListAll(type)).setMessage("获取成功");
    }

    @GetMapping("/get/all")
    @LogAnnotation(operate = "获取所有用户列表")
    public Result getUserAll() {
        return Result.Success(userService.getUserAll()).setMessage("query all of list success");
    }

    @GetMapping("/get/page")
    @LogAnnotation(operate = "分页获取用户列表")
    public Result getUserPage(Integer currentPage, Integer pageSize, User query) {
        return Result.Success(userService.getUserPage(currentPage, pageSize, query)).setMessage("query pagination list success");
    }

    @PostMapping("/insert")
    @LogAnnotation(operate = "新增用户")
    public Result addUser(@RequestBody User user) {
        return userService.addUser(user) > 0 ? Result.Success().setMessage("insert success") : Result.Error().setMessage("insert fail");
    }

    @DeleteMapping("/delete/{id}")
    @LogAnnotation(operate = "删除用户")
    public Result delUser(@PathVariable("id") Long id) {
        return userService.delUser(id) > 0 ? Result.Success().setMessage("delete success") : Result.Error().setMessage("delete fail");
    }

    @DeleteMapping("/delete/batch")
    @LogAnnotation(operate = "批量删除用户")
    public Result delBatchUser(@RequestBody List<Long> ids) {
        return userService.delBatchUser(ids) > 0 ? Result.Success().setMessage("batch delete success") : Result.Error().setMessage("batch delete fail");

    }

    @PutMapping("/update")
    @LogAnnotation(operate = "更新用户")
    public Result updateUser(@RequestBody User user) {
        return userService.updateUser(user) > 0 ? Result.Success().setMessage("update success") : Result.Error().setMessage("update fail");
    }

    @GetMapping("/center")
    @LogAnnotation(operate = "个人中心")
    public Result center(String username) {
        return Result.Success(userService.selectOneByUsername(username));
    }

}
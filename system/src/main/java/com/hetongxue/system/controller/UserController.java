package com.hetongxue.system.controller;

import com.hetongxue.aop.annotation.LogAnnotation;
import com.hetongxue.base.constant.Base;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.exception.JwtAuthenticationException;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.MenuVO;
import com.hetongxue.system.domain.vo.RouterVO;
import com.hetongxue.system.domain.vo.TokenVO;
import com.hetongxue.system.domain.vo.UserVO;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

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
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisUtils redisUtils;

    @GetMapping("/getUserInfo")
    @LogAnnotation(operate = "获取当前登录用户信息", detail = "获取当前已经登陆用户的路由、菜单及基本信息")
    public Result getUserInfo(HttpServletResponse response) {
        // 获取用户信息
        User user = userService.selectOneByUsername(SecurityUtils.getUser().getUsername());
        if (!ObjectUtils.isEmpty(user)) {

            // 获取角色列表
            List<Role> roleList = roleService.selectRoleListByUserId(user.getUserId());
            // 获取菜单列表
            List<Menu> menuList = menuService.selectMenuListByUserId(user.getUserId());
            // 生成菜单列表
            List<MenuVO> menus = SecurityUtils.generateMenu(menuList, 0L);
            // 生成路由列表
            List<RouterVO> routers = SecurityUtils.generateRouter(menuList, 0L);
            // 生成权限数组
            String[] permissions = SecurityUtils.generatePermissionToArray(menuList);
            // 生成角色数组
            String[] roles = SecurityUtils.generateRoleToArray(roleList);

            response.setStatus(HttpStatus.OK.value());
            return Result.Success(new UserVO(user.getNickName(), user.getAvatar(), roles, user.getIsAdmin(), permissions, menus, routers)).setMessage("获取用户信息成功");
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.Error().setMessage("获取用户信息失败").setCode(HttpStatus.UNAUTHORIZED.value());
    }

    @PostMapping("/refreshToken")
    @LogAnnotation(operate = "刷新令牌", detail = "前端过期时间刷新当前令牌")
    public Result refreshToken(HttpServletRequest request) {

        try {
            String token = request.getHeader(Base.SECURITY_AUTHORIZATION);
            if (ObjectUtils.isEmpty(token)) {
                throw new JwtAuthenticationException("token异常");
            }
            if (ObjectUtils.isEmpty(jwtUtils.getClaims(token))) {
                throw new JwtAuthenticationException("token异常");
            }
            String newToken = jwtUtils.refreshToken(token);
            // 删除之前token
            redisUtils.delete(Base.SECURITY_AUTHORIZATION);
            // 设置新的token
            redisUtils.setValue(Base.SECURITY_AUTHORIZATION, newToken, Base.REDIS_TIMEOUT, Base.REDIS_TIMEUNIT);
            return Result.Success(new TokenVO(newToken, Base.TOKEN_EXPIRATION_TIME)).setMessage("刷新token成功！");
        } catch (JwtAuthenticationException e) {
            return Result.Error().setMessage("刷新token失败,请重新登录！");
        }
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
    public Result getUserPage(Integer currentPage, Integer pageSize, User user) {
        return Result.Success(userService.getUserPage(currentPage, pageSize, user)).setMessage("query pagination list success");
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

}
package com.hetongxue.system.controller;

import com.hetongxue.base.constant.Base;
import com.hetongxue.base.response.Result;
import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.configuration.security.exception.JwtAuthenticationException;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.domain.vo.MenuVo;
import com.hetongxue.system.domain.vo.RouterVo;
import com.hetongxue.system.domain.vo.TokenVo;
import com.hetongxue.system.domain.vo.UserVo;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public Result getUserInfo(HttpServletResponse response) {
        // 获取账户信息
        Long accountId =  SecurityUtils.getAccount().getAccountId();
        // 获取用户信息
        User user = userService.selectOneByAccountID(accountId);
        if (!ObjectUtils.isEmpty(user)) {
            // 获取角色列表
            List<Role> roleList = roleService.selectRoleByAccountId(accountId);
            // 获取菜单列表
            List<Menu> menuList = menuService.selectMenuListByAccountID(accountId);
            List<MenuVo> menus = SecurityUtils.generateMenu(menuList, 0L);
            // 获取路由列表
            List<RouterVo> routers = SecurityUtils.generateRouter(menuList, 0L);
            // 生成权限数组
            String[] permissions = SecurityUtils.generatePermissionToArray(menuList);
            // 生成角色数组
            String[] roles = SecurityUtils.generateRoleToArray(roleList);
            // 设置响应状态
            response.setStatus(HttpStatus.OK.value());
            // 返回UserVo值
            return Result.Success(new UserVo(user.getNickName(), user.getAvatar(), roles, false, permissions, menus, routers)).setMessage("获取用户信息成功");
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        return Result.Error().setMessage("获取用户信息失败").setCode(HttpStatus.UNAUTHORIZED.value());
    }


    @PostMapping("/refreshToken")
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
            return Result.Success(new TokenVo(newToken, Base.TOKEN_EXPIRATION_TIME)).setMessage("刷新token成功！");
        } catch (JwtAuthenticationException e) {
            return Result.Error().setMessage("刷新token失败,请重新登录！");
        }
    }

}
package com.hetongxue.system.controller;

import com.hetongxue.configuration.redis.RedisUtils;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
import com.hetongxue.utils.JwtUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户模块
 *
 * @author 何同学
 */
@RestController
@RequestMapping("/auth")
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

//    @GetMapping("/getUserInfo")
//    public Result getUserInfo(HttpServletResponse response) {
//        // 获取账户信息
//        Long accountId = SecurityUtils.getAccount().getAccountId();
//        // 获取用户信息
//        User user = userService.selectOneByAccountID(accountId);
//        if (!ObjectUtils.isEmpty(user)) {
//
//            // 获取角色列表
//            List<Role> roleList = roleService.selectRoleByAccountId(accountId);
//
//            // 获取菜单列表
//            List<Menu> menuList = menuService.selectMenuListByAccountID(accountId);
//            List<MenuVo> menus = SecurityUtils.generateMenu(menuList, 0L);
//
//            // 获取路由列表
//            List<RouterVo> routers = SecurityUtils.generateRouter(menuList, 0L);
//
//            // 生成权限列表
//            String authority = SecurityUtils.generateAuthorityByKey(menuList);
//
//            response.setStatus(HttpStatus.OK.value());
//            return Result.Success(new UserVo(roleList.get(0).getRoleKey(), authority, routers, menus)).setMessage("获取用户信息成功");
//        }
//        response.setStatus(HttpStatus.UNAUTHORIZED.value());
//        return Result.Error().setMessage("获取用户信息失败");
//    }
}
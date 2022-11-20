package com.hetongxue.system.service.impl;

import com.hetongxue.configuration.security.entity.LoginInfo;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Account;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.vo.MenuVo;
import com.hetongxue.system.domain.vo.RouterVo;
import com.hetongxue.system.service.AccountService;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * UserDetailsService实现类
 *
 * @author 何同学
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private AccountService accountService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountService.selectOneByUsername(username);
        if (Objects.isNull(account)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        // 查询角色列表
        List<Role> roleList = roleService.selectRoleByAccountId(account.getAccountId());
        // 查询菜单列表
        List<Menu> menuList = menuService.selectMenuListByAccountID(account.getAccountId());
        // 生成权限列表
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(SecurityUtils.generateAuthority(roleList, menuList));
        // 生成菜单列表
        List<MenuVo> menus = SecurityUtils.generateMenu(menuList, 0L);
        // 生成路由列表
        List<RouterVo> routers = SecurityUtils.generateRouter(menuList, 0L);

        System.out.println("routers = " + routers);
        System.out.println("menuList = " + menuList);
        System.out.println("authorities = " + authorities);
        System.out.println("menus = " + menus);
        System.out.println("routers = " + routers);

        return new LoginInfo(account, authorities);
    }

}
package com.hetongxue.system.service.impl;

import com.hetongxue.configuration.security.entity.UserDetail;
import com.hetongxue.configuration.security.utils.SecurityUtils;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.User;
import com.hetongxue.system.service.MenuService;
import com.hetongxue.system.service.RoleService;
import com.hetongxue.system.service.UserService;
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
    private UserService userService;
    @Resource
    private RoleService roleService;
    @Resource
    private MenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.selectOneByUsername(username);
        if (Objects.isNull(user)) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }

        // 查询角色列表
        List<Role> roleList = roleService.selectRoleListByUserId(user.getUserId());
        // 查询菜单列表
        List<Menu> menuList = menuService.selectMenuListByUserId(user.getUserId());
        // 生成权限列表
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(SecurityUtils.generateAuthority(roleList, menuList));
        
        return new UserDetail(user, authorities);
    }

}
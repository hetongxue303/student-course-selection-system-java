package com.hetongxue.configuration.security.utils;

import com.hetongxue.system.domain.Account;
import com.hetongxue.system.domain.Menu;
import com.hetongxue.system.domain.Role;
import com.hetongxue.system.domain.vo.MenuVo;
import com.hetongxue.system.domain.vo.RouterVo;
import com.hetongxue.system.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * security工具类
 *
 * @author 何同学
 */
public class SecurityUtils {

    @Resource
    private UserService userService;

    /**
     * 目录key
     */
    private static final int LIST_KEY = 0;
    /**
     * 菜单项key
     */
    private static final int MENUITEM_KEY = 1;
    /**
     * 按钮key
     */
    private static final int BUTTON_KEY = 2;

    /**
     * 生成菜单列表
     *
     * @param menuList 菜单列表
     * @param parentId 父ID
     * @return java.util.List<com.hetongxue.system.domain.vo.MenuVo> - 菜单列表
     */
    public static List<MenuVo> generateMenu(List<Menu> menuList, Long parentId) {
        List<MenuVo> menus = new ArrayList<>();
        // 判断是否为空
        Optional.ofNullable(menuList)
                // 不为空时创建新的数组
                .orElse(new ArrayList<Menu>())
                // 转换流
                .stream()
                // 过滤 不为空 和 对应父ID 的数据 以及类型不为 按钮 的
                .filter(item -> item != null && Objects.equals(item.getParentId(), parentId) && item.getMenuType() != BUTTON_KEY)
                // 循环遍历
                .forEach(item -> {
                    menus.add(new MenuVo().setName(item.getMenuTitle()).setPath(item.getPath()).setIcon(item.getIcon()).setChildren(generateMenu(menuList, item.getMenuId())));
                });
        return menus;
    }

    /**
     * 生成路由列表
     *
     * @param menuList 菜单列表
     * @param parentId 父ID
     * @return java.util.List<com.hetongxue.system.domain.vo.RouterVo> - 路由列表
     */
    public static List<RouterVo> generateRouter(List<Menu> menuList, Long parentId) {
        List<RouterVo> routers = new ArrayList<>();
        // 判断是否为空
        Optional.ofNullable(menuList)
                // 不为空时创建新的数组
                .orElse(new ArrayList<Menu>())
                // 转成流
                .stream()
                // 过滤 不为空 和 对应父ID 的数据 以及类型不为 按钮 的
                .filter(item -> item != null && Objects.equals(item.getParentId(), parentId) && item.getMenuType() != BUTTON_KEY)
                // 遍历循环
                .forEach(item -> {
                    routers.add(new RouterVo().setName(item.getMenuName()).setPath(item.getPath()).setComponent(item.getComponent()).setMeta(new RouterVo.MetaVo().setTitle(item.getMenuTitle()).setIcon(item.getIcon()).setKeepAlive(true).setRequireAuth(true)
                            // 当类型是目录时 不存在权限代码
                            .setRoles(item.getMenuType() != LIST_KEY ? menuList.stream()
                                    // 过滤权限代码 不为空 且 不能是目录
                                    .filter(strip -> strip.getPerKey() != null)
                                    // 过滤父ID等于当前ID的数据(此时不存在list权限 若要存在list权限 则过滤排序顺序一致的数据即可)
                                    .filter(strip -> Objects.equals(strip.getParentId(), item.getMenuId()))
                                    // 获得权限编码
                                    .map(Menu::getPerKey)
                                    // 生成string数组
                                    .toArray(String[]::new) : null)).setChildren(generateRouter(menuList, item.getMenuId())));
                });
        return routers;
    }

    /**
     * 生成权限信息
     *
     * @param roles 角色列表
     * @param menus 菜单列表
     * @return java.lang.String - 权限列表
     */
    public static String generateAuthority(List<Role> roles, List<Menu> menus) {
        // 获取角色代码列表
        String role = Optional.ofNullable(roles).orElse(new ArrayList<Role>()).stream().filter(Objects::nonNull).map(item -> "ROLE_" + item.getRoleKey()).collect(Collectors.joining(","));
        // 获取权限代码列表
        String permission = Optional.ofNullable(menus).orElse(new ArrayList<Menu>()).stream().filter(Objects::nonNull).map(Menu::getPerKey).filter(Objects::nonNull).collect(Collectors.joining(","));
        // 判断角色列表是否为空 为空则只返回权限代码 不为空则返回角色列表+权限代码列表
        return ObjectUtils.isEmpty(role) ? permission : role.concat(",").concat(permission);
    }

    /**
     * 生成权限key值
     *
     * @param menus 菜单列表
     * @return java.lang.String - 权限列表
     */
    public static String generateAuthorityByKey(List<Menu> menus) {
        return Optional.ofNullable(menus).orElse(new ArrayList<Menu>()).stream().filter(Objects::nonNull).map(Menu::getPerKey).filter(Objects::nonNull).collect(Collectors.joining(","));
    }

    /**
     * 获取SecurityContextHolder
     *
     * @return org.springframework.security.core.Authentication
     */
    public static Authentication getSecurityContextHolder() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取账户信息
     *
     * @return com.hetongxue.system.domain.Account
     */
    public static Account getAccount() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
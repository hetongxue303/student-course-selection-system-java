package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表现层对象：用户信息
 *
 * @author 何同学
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVO implements Serializable {

    /**
     * 用户名
     */
    private String username;
    /**
     * 用户头型
     */
    private String avatar;
    /**
     * 角色信息
     */
    private String[] roles;
    /**
     * 是否管理员
     */
    private Boolean isAdmin;
    /**
     * 权限列表
     */
    private String[] permissions;
    /**
     * 菜单列表
     */
    private List<MenuVO> menus;
    /**
     * 路由列表
     */
    private List<RouterVO> routers;

}
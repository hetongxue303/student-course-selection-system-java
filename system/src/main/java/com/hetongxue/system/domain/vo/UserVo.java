package com.hetongxue.system.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户信息返回类
 *
 * @author 何同学
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserVo implements Serializable {

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
    private List<MenuVo> menus;
    /**
     * 路由列表
     */
    private List<RouterVo> routers;

}
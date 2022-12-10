package com.hetongxue.system.domain.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 表现层对象：菜单
 *
 * @author 何同学
 */
@Data
@Accessors(chain = true)
public class MenuVO implements Serializable {

    /**
     * 菜单名称
     */
    private String name;
    /**
     * 菜单地址
     */
    private String path;
    /**
     * 菜单图标
     */
    private String icon;
    /**
     * 子菜单
     */
    private List<MenuVO> children;

}
package com.hetongxue.system.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 业务对象：菜单信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MenuBO implements Serializable {


    /**
     * 菜单ID
     */
    private Long menuId;
    /**
     * 菜单名字
     */
    private String menuName;
    /**
     * 菜单标题
     */
    private String menuTitle;
    /**
     * 菜单类型
     */
    private Integer menuType;
    /**
     * 父ID
     */
    private Long parentId;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 路由组件
     */
    private String component;
    /**
     * 菜单排序
     */
    private Integer sort;
    /**
     * 图标名称
     */
    private String icon;
    /**
     * 权限标识
     */
    private String perKey;
    /**
     * 是否显示(0:否 1:是)
     */
    private Boolean isDisplay;
    /**
     * 是否外链(0:否 1:是)
     */
    private Boolean isFrame;
    /**
     * 是否缓存(0:否 1:是)
     */
    private Boolean isCache;
    /**
     * 是否删除(0:否 1:是)
     */
    private Boolean isDelete;
    /**
     * 备注
     */
    private String remark;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;
    /**
     * 是否为根节点
     */
    private Boolean hasChildren;
    /**
     * 子菜单
     */
    private List<MenuBO> children;

}
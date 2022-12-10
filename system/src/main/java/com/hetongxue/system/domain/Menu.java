package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久对象：菜单信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_menu")
public class Menu implements Serializable {

    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
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
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

}
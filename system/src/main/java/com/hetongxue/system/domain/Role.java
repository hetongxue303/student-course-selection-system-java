package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 持久对象：角色信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_role")
public class Role implements Serializable {
    /**
     * 角色ID
     */
    @TableId(type = IdType.AUTO)
    private Long roleId;
    /**
     * 角色名称
     */
    private String roleName;
    /**
     * 角色key值
     */
    private String roleKey;
    /**
     * 角色级别
     */
    private Integer level;
    /**
     * 数据权限
     */
    private String dataScope;
    /**
     * 角色描述
     */
    private String description;
    /**
     * 是否启用(1是 0否)
     */
    private Boolean isEnable;
    /**
     * 是否删除(1是 0否)
     */
    private Boolean isDelete;
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
    /**
     * 菜单列表
     */
    @TableField(exist = false)
    private List<Menu> menus;
}
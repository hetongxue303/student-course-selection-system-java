package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户实体
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_user")
public class User implements Serializable {

    /**
     * 用户ID
     */
    @TableId(type = IdType.AUTO)
    private Long userId;
    /**
     * 学院ID
     */
    private Long cId;
    /**
     * 专业ID
     */
    private Long mId;
    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 学号/工号
     */
    private Long userNo;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户性别(0:男 1:女 2:保密)
     */
    private String gender;
    /**
     * 头像地址
     */
    private String avatar;
    /**
     * 帐号状态(0:正常 1:停用)
     */
    private boolean status;
    /**
     * 删除标志(0:存在 1:已删除)
     */
    private boolean delFlag;
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
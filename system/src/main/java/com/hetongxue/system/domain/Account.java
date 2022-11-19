package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户实体
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_account")
public class Account implements Serializable {

    /**
     * 账户ID
     */
    @TableId(type = IdType.AUTO)
    private Long accountId;
    /**
     * 账户名
     */
    private String username;
    /**
     * 账户密码
     */
    private String password;
    /**
     * 账户状态
     */
    private boolean status;
    /**
     * 删除状态
     */
    private boolean delFlag;
    /**
     * 最后登录IP
     */
    private String lastLoginIp;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
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
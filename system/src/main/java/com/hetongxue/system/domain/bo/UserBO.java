package com.hetongxue.system.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务对象：用户信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserBO implements Serializable {


    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户昵称
     */
    private String nickName;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 用户性别(1:男 2:女 3:保密)
     */
    private String gender;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 用户电话
     */
    private String phone;
    /**
     * 用户类型
     */
    private Integer type;
    /**
     * 是否删除(1是 0否)
     */
    private Boolean isDelete;
    /**
     * 是否启用(0:正常 1:停用)
     */
    private Boolean isEnable;
    /**
     * 是否管理员(1是 0否)
     */
    private Boolean isAdmin;
    /**
     * 用户备注
     */
    private String remark;
    /**
     * 最后登录ip
     */
    private String lastLoginIp;
    /**
     * 最后登录时间
     */
    private Date lastLoginTime;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久对象：专业信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_major")
public class Major implements Serializable {
    /**
     * 专业ID
     */
    @TableId(type = IdType.AUTO)
    private Long majorId;
    /**
     * 专业名称
     */
    private String majorName;
    /**
     * 学院ID
     */
    private Long collegeId;
    /**
     * 学院信息
     */
    @TableField(exist = false)
    private College college;
    /**
     * 专业描述
     */
    private String remark;
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
}
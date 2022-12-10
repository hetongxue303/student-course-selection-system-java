package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久对象：学院信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_college")
public class College implements Serializable {

    /**
     * 学院ID
     */
    @TableId(type = IdType.AUTO)
    private Long collegeId;
    /**
     * 学院名称
     */
    private String collegeName;
    /**
     * 学院描述
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
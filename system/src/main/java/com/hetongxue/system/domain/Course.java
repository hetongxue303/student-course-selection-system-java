package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 持久对象：课程信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_course")
public class Course implements Serializable {
    /**
     * 课程ID
     */
    @TableId(type = IdType.AUTO)
    private Long courseId;
    /**
     * 任课老师
     */
    private Long userId;
    /**
     * 用户信息
     */
    @TableField(exist = false)
    private User user;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程人数
     */
    private Integer count;
    /**
     * 选课人数
     */
    private Integer choice;
    /**
     * 课程描述
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
    /**
     * 是否已选
     */
    @TableField(exist = false)
    private Boolean isChoice;
    /**
     * 是否同意
     */
    @TableField(exist = false)
    private Boolean isConfirm;
    /**
     * 任课老师
     */
    @TableField(exist = false)
    private String teacherName;
}
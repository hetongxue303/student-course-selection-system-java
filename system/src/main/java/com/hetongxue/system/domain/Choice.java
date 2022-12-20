package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 持久对象：选课信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_choice")
public class Choice implements Serializable {

    /**
     * 选课ID
     */
    @TableId(type = IdType.AUTO)
    private Long choiceId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 课程成绩
     */
    private BigDecimal score;
    /**
     * 是否退选
     */
    private Boolean isQuit;
    /**
     * 是否删除
     */
    private Boolean isDelete;
    /**
     * 是否同意
     */
    private Integer status;
    /**
     * 是否结课
     */
    private Boolean isEnd;
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
     * 课程名称
     */
    @TableField(exist = false)
    private String courseName;
    /**
     * 用户名字
     */
    @TableField(exist = false)
    private String realName;
    /**
     * 任课教师
     */
    @TableField(exist = false)
    private String teacherName;

}
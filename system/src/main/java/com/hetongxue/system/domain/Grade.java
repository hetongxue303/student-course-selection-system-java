package com.hetongxue.system.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 班级类
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_grade")
public class Grade implements Serializable {
    /**
     * 班级ID
     */
    @TableId(type = IdType.AUTO)
    private Long gradeId;
    /**
     * 班级名称
     */
    private String gradeName;
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
     * 专业ID
     */
    private Long majorId;
    /**
     * 专业信息
     */
    @TableField(exist = false)
    private Major major;
    /**
     * 班级人数
     */
    private Integer gradeCount;
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
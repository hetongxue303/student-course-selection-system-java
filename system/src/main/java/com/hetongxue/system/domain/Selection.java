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
 * 选课表
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("sys_selection")
public class Selection implements Serializable {

    /**
     * 选课ID
     */
    @TableId(type = IdType.AUTO)
    private Long selectionId;
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

}
package com.hetongxue.system.domain.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 业务对象：课程信息
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CourseBO implements Serializable {

    /**
     * 课程ID
     */
    private Long courseId;
    /**
     * 任课老师
     */
    private Long userId;
    /**
     * 课程名称
     */
    private String courseName;
    /**
     * 课程人数
     */
    private Integer count;
    /**
     * 已选人数
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
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;

}
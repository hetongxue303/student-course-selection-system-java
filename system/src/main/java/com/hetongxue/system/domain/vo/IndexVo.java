package com.hetongxue.system.domain.vo;

import com.hetongxue.system.domain.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 首页VO
 *
 * @author 何同学
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class IndexVo implements Serializable {

    /**
     * 学生人数
     */
    private Integer studentCount;
    /**
     * 教师人数
     */
    private Integer teacherCount;
    /**
     * 专业数量
     */
    private Integer majorCount;
    /**
     * 课程数目
     */
    private Integer courseCount;
    /**
     * 课程表格
     */
    private List<Course> courses;

}
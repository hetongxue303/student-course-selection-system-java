package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.vo.QueryVo;

import java.util.List;

/**
 * 课程业务
 *
 * @author 何同学
 */
public interface CourseService extends IService<Course> {

    /**
     * 获取所有课程列表
     *
     * @return List<Course>
     */
    List<Course> getCourseAll();

    /**
     * 分页获取课程列表
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param name        课程名
     * @return QueryVo
     */
    QueryVo getCoursePage(Integer currentPage, Integer pageSize, String name);

}
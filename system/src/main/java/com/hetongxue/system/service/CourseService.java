package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Course;
import com.hetongxue.system.domain.vo.QueryVO;

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
     * @param query       查询信息
     * @return QueryVo
     */
    QueryVO getCoursePage(Integer currentPage, Integer pageSize, Course query);

    QueryVO getMyCoursePage(Integer currentPage, Integer pageSize, Course query);


    /**
     * 添加课程
     *
     * @param course 课程信息
     * @return int
     */
    int addCourse(Course course);

    /**
     * 删除课程
     *
     * @param id 课程D
     * @return int
     */
    int delCourse(Long id);

    /**
     * 批量删除课程
     *
     * @param ids 学课ID
     * @return int
     */
    int delBatchCourse(List<Long> ids);

    /**
     * 更新课程
     *
     * @param course 课程信息
     * @return int
     */
    int updateCourse(Course course);


}
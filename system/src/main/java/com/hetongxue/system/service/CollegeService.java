package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.domain.vo.QueryVo;

import java.util.List;

/**
 * 学院业务
 *
 * @author 何同学
 */
public interface CollegeService extends IService<College> {

    /**
     * 获取学院列表 - All
     *
     * @return java.util.List<com.hetongxue.system.domain.College>
     */
    List<College> getCollegeAll();

    /**
     * 添加学院
     *
     * @param college 学院信息
     * @return boolean
     */
    boolean addCollege(College college);

    /**
     * 获取学院列表 - Page
     *
     * @param page 当前页
     * @param size 页面大小
     * @param name 学院名
     * @return QueryVo
     */
    QueryVo getCollegePage(Integer page, Integer size, String name);

    /**
     * 删除学院
     *
     * @param id 学院ID
     * @return boolean
     */
    boolean delCollege(Long id);
}
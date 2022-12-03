package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.College;

import java.util.List;

/**
 * 学院业务
 *
 * @author 何同学
 */
public interface CollegeService extends IService<College> {

    /**
     * 获取所有学院列表
     *
     * @return java.util.List<com.hetongxue.system.domain.College>
     */
    List<College> getCollegeAll();

}
package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Grade;
import com.hetongxue.system.domain.vo.QueryVO;

import java.util.List;

/**
 * 班级业务
 *
 * @author 何同学
 */
public interface GradeService extends IService<Grade> {

    /**
     * 获取所有班级信息
     */
    List<Grade> getGradeAll();

    /***
     * 分页查询班级信息
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @param query 查询参数
     */
    QueryVO getGradePage(Integer currentPage, Integer pageSize, Grade query);

    /**
     * 新增班级
     */
    int addGrade(Grade grade);

    /**
     * 删除班级
     */
    int delGrade(Long id);

    /**
     * 批量删除班级
     */
    int delBatchGrade(List<Long> ids);

    /**
     * 更新班级信息
     */
    int updateGrade(Grade grade);
}
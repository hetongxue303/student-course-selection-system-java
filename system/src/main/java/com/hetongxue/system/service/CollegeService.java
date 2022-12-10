package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.domain.vo.QueryVO;

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
     * @return List<College>
     */
    List<College> getCollegeAll();

    /**
     * 分页获取学院列表
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param name        学院名
     * @return QueryVo
     */
    QueryVO getCollegePage(Integer currentPage, Integer pageSize, String name);

    /**
     * 添加学院
     *
     * @param college 学院信息
     * @return int
     */
    int addCollege(College college);

    /**
     * 删除学院
     *
     * @param id 学院ID
     * @return int
     */
    int delCollege(Long id);

    /**
     * 批量删除学院
     *
     * @param ids 学院ID
     * @return int
     */
    int delBatchCollege(List<Long> ids);

    /**
     * 更新学院
     *
     * @param college 学院信息
     * @return int
     */
    int updateCollege(College college);

}
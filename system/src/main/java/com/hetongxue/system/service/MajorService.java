package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Major;
import com.hetongxue.system.domain.vo.QueryVO;

import java.util.List;

/**
 * 专业业务
 *
 * @author 何同学
 */
public interface MajorService extends IService<Major> {

    /**
     * 获取所有专业列表
     *
     * @return List<Major>
     */
    List<Major> getMajorAll();

    /**
     * 分页获取专业列表
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param query       查询信息
     * @return QueryVo
     */
    QueryVO getMajorPage(Integer currentPage, Integer pageSize, Major query);

    /**
     * 添加专业
     *
     * @param major 专业信息
     * @return int
     */
    int addMajor(Major major);

    /**
     * 删除专业
     *
     * @param id 专业ID
     * @return int
     */
    int delMajor(Long id);

    /**
     * 批量删除专业
     *
     * @param ids 专业ID
     * @return int
     */
    int delBatchMajor(List<Long> ids);

    /**
     * 更新专业
     *
     * @param major 专业信息
     * @return int
     */
    int updateMajor(Major major);

}
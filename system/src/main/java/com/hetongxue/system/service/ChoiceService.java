package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Choice;
import com.hetongxue.system.domain.vo.QueryVO;

import java.util.List;

/**
 * 选课业务
 *
 * @author 何同学
 */
public interface ChoiceService extends IService<Choice> {

    /**
     * 获取所有选课记录列表
     *
     * @return List<Choice>
     */
    List<Choice> getChoiceAll();

    /**
     * 分页获取选课记录列表
     *
     * @param currentPage 当前页
     * @param pageSize    页面大小
     * @param query       查询信息
     * @return QueryVo
     */
    QueryVO getChoicePage(Integer currentPage, Integer pageSize, Choice query);

    /**
     * 添加选课记录
     *
     * @param choice 选课记录信息
     * @return int
     */
    int addChoice(Choice choice);

    /**
     * 删除选课记录
     *
     * @param id 选课ID
     * @return int
     */
    int delChoice(Long id);

    /**
     * 批量删除选课记录
     *
     * @param ids 选课ID
     * @return int
     */
    int delBatchChoice(List<Long> ids);

    /**
     * 更新选课记录
     *
     * @param choice 选课记录信息
     * @return int
     */
    int updateChoice(Choice choice);

    /**
     * 学生选课处理
     *
     * @param type     类型(1选课 2退选)
     * @param courseId 课程ID
     */
    int studentChoiceCourse(Integer type, Long courseId);

}
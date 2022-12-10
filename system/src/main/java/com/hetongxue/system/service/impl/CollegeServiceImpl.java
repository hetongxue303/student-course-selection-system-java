package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.CollegeMapper;
import com.hetongxue.system.service.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * 学院业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Resource
    private CollegeMapper collegeMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<College> getCollegeAll() {
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(College::getIsDelete, false);
        wrapper.orderByAsc(College::getCollegeId);
        return collegeMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getCollegePage(Integer currentPage, Integer pageSize, String name) {
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(name)) {
            wrapper.like(College::getCollegeName, name);
        }
        wrapper.eq(College::getIsDelete, false);
        wrapper.orderByAsc(College::getCollegeId);
        Page<College> list = collegeMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), list.getRecords());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addCollege(College college) {
        return collegeMapper.insert(college);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delCollege(Long id) {
        return collegeMapper.updateById(new College().setCollegeId(id).setIsDelete(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchCollege(List<Long> ids) {
        try {
            ids.forEach(id -> {
                collegeMapper.updateById(new College().setCollegeId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCollege(College college) {
        return collegeMapper.updateById(college);
    }
}
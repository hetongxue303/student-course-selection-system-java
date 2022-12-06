package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.domain.vo.QueryVo;
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
    @Transactional(rollbackFor = Exception.class)
    public boolean addCollege(College college) {
        return collegeMapper.insert(college) > 0;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVo getCollegePage(Integer page, Integer size, String name) {
        LambdaQueryWrapper<College> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(name)) {
            wrapper.like(College::getCollegeName, name);
        }
        wrapper.eq(College::getIsDelete, false);
        wrapper.orderByAsc(College::getCollegeId);
        Page<College> list = collegeMapper.selectPage(new Page<>(page, size), wrapper);
        return new QueryVo(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), list.getRecords());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delCollege(Long id) {
        // 逻辑删除
        return collegeMapper.updateById(new College().setCollegeId(id).setIsDelete(true)) > 0;
        // 物理删除
//        return collegeMapper.deleteById(id) > 0;
    }
}
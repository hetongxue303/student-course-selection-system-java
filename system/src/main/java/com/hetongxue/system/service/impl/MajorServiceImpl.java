package com.hetongxue.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Major;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.repository.CollegeMapper;
import com.hetongxue.system.repository.MajorMapper;
import com.hetongxue.system.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 专业业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class MajorServiceImpl extends ServiceImpl<MajorMapper, Major> implements MajorService {

    @Resource
    private MajorMapper majorMapper;
    @Resource
    private CollegeMapper collegeMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Major> getMajorAll() {
        LambdaQueryWrapper<Major> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Major::getIsDelete, false);
        wrapper.orderByAsc(Major::getMajorId);
        return majorMapper.selectList(wrapper);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getMajorPage(Integer currentPage, Integer pageSize, Major query) {
        LambdaQueryWrapper<Major> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(query.getMajorName())) {
            wrapper.like(Major::getMajorName, query.getMajorName());
        }
        wrapper.eq(Major::getIsDelete, false);
        wrapper.orderByAsc(Major::getMajorId);
        Page<Major> list = majorMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);

        List<Major> majors = new ArrayList<>();
        Optional.ofNullable(list.getRecords()).orElse(new ArrayList<>()).forEach(item -> majors.add(item.setCollege(collegeMapper.selectById(item.getCollegeId()))));

        return new QueryVO(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), majors);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addMajor(Major major) {
        return majorMapper.insert(major);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delMajor(Long id) {
        return majorMapper.updateById(new Major().setMajorId(id).setIsDelete(true));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchMajor(List<Long> ids) {
        try {
            ids.forEach(id -> {
                majorMapper.updateById(new Major().setMajorId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMajor(Major major) {
        return majorMapper.updateById(major);

    }

}
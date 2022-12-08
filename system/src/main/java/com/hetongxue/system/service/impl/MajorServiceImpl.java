package com.hetongxue.system.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Major;
import com.hetongxue.system.domain.vo.QueryVo;
import com.hetongxue.system.mapper.MajorMapper;
import com.hetongxue.system.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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
    public QueryVo getMajorPage(Integer page, Integer size, String name) {
        LambdaQueryWrapper<Major> wrapper = new LambdaQueryWrapper<>();
        if (Objects.nonNull(name)) {
            wrapper.like(Major::getMajorName, name);
        }
        wrapper.eq(Major::getIsDelete, false);
        wrapper.orderByAsc(Major::getMajorId);
        Page<Major> list = majorMapper.selectPage(new Page<>(page, size), wrapper);
        return new QueryVo(list.getCurrent(), list.getSize(), list.getTotal(), list.getPages(), list.getRecords());

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
        return majorMapper.deleteBatchIds(ids);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateMajor(Major major) {
        return majorMapper.updateById(major);

    }

}
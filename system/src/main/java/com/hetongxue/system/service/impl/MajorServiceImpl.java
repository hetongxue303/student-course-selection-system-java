package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Major;
import com.hetongxue.system.mapper.MajorMapper;
import com.hetongxue.system.service.MajorService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

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
        return majorMapper.selectList(new LambdaQueryWrapper<Major>().orderByAsc(Major::getMajorId));
    }

}
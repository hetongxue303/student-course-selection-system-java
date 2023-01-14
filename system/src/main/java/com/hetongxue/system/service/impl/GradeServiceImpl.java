package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Grade;
import com.hetongxue.system.domain.vo.QueryVO;
import com.hetongxue.system.mapper.CollegeMapper;
import com.hetongxue.system.mapper.GradeMapper;
import com.hetongxue.system.mapper.MajorMapper;
import com.hetongxue.system.service.GradeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 班级业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class GradeServiceImpl extends ServiceImpl<GradeMapper, Grade> implements GradeService {

    @Resource
    private GradeMapper gradeMapper;
    @Resource
    private CollegeMapper collegeMapper;
    @Resource
    private MajorMapper majorMapper;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Grade> getGradeAll() {
        return gradeMapper.selectList(null);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public QueryVO getGradePage(Integer currentPage, Integer pageSize, Grade query) {
        LambdaQueryWrapper<Grade> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Grade::getIsDelete, false);
        wrapper.eq(Objects.nonNull(query.getGradeName()), Grade::getGradeName, query.getGradeName());
        Page<Grade> page = gradeMapper.selectPage(new Page<>(currentPage, pageSize), wrapper);
        List<Grade> grades = new ArrayList<>();
        Optional.ofNullable(page.getRecords()).orElse(new ArrayList<>()).forEach(item -> {
            item.setCollege(collegeMapper.selectById(item.getCollegeId()));
            item.setMajor(majorMapper.selectById(item.getMajorId()));
            grades.add(item);
        });
        return new QueryVO(page.getCurrent(), page.getSize(), page.getTotal(), page.getPages(), grades);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int addGrade(Grade grade) {
        return gradeMapper.insert(grade);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delGrade(Long id) {
        return gradeMapper.updateById(new Grade().setIsDelete(true).setGradeId(id));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int delBatchGrade(List<Long> ids) {
        try {
            ids.forEach(id -> {
                gradeMapper.updateById(new Grade().setGradeId(id).setIsDelete(true));
            });
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGrade(Grade grade) {
        return gradeMapper.updateById(grade);
    }
    
}
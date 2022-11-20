package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.College;
import com.hetongxue.system.mapper.CollegeMapper;
import com.hetongxue.system.service.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 学院业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class CollegeServiceImpl extends ServiceImpl<CollegeMapper, College> implements CollegeService {

}
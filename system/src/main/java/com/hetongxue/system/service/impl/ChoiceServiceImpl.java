package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Choice;
import com.hetongxue.system.mapper.ChoiceMapper;
import com.hetongxue.system.service.ChoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 选课业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class ChoiceServiceImpl extends ServiceImpl<ChoiceMapper, Choice> implements ChoiceService {

}
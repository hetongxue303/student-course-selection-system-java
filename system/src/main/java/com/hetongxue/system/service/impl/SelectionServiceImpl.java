package com.hetongxue.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hetongxue.system.domain.Selection;
import com.hetongxue.system.mapper.SelectionMapper;
import com.hetongxue.system.service.SelectionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 选课业务实现
 *
 * @author 何同学
 */
@Service
@Transactional
public class SelectionServiceImpl extends ServiceImpl<SelectionMapper, Selection> implements SelectionService {

}
package com.hetongxue.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hetongxue.system.domain.Major;

import java.util.List;

/**
 * 专业业务
 *
 * @author 何同学
 */
public interface MajorService extends IService<Major> {

    /**
     * 获取所有专业列表
     *
     * @return java.util.List<com.hetongxue.system.domain.Major>
     */
    List<Major> getMajorAll();

}
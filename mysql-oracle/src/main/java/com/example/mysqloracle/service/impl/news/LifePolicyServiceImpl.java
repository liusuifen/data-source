package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.news.LifePolicyMapper;
import com.example.mysqloracle.entity.news.LifePolicy;
import com.example.mysqloracle.service.news.LifePolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 寿险保单-投保单主表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Service
public class LifePolicyServiceImpl extends ServiceImpl<LifePolicyMapper, LifePolicy> implements LifePolicyService {

    @Autowired
    private LifePolicyMapper lifePolicyMapper;

    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public LifePolicy getById(Long id) {
        return lifePolicyMapper.getById(id);
    }
}

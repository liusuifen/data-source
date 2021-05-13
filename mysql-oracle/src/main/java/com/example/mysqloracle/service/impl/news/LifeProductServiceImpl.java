package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;

import com.example.mysqloracle.dao.news.LifeProductMapper;
import com.example.mysqloracle.entity.news.LifeProduct;
import com.example.mysqloracle.service.news.LifeProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 寿险产品库 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Service
public class LifeProductServiceImpl extends ServiceImpl<LifeProductMapper, LifeProduct> implements LifeProductService {

    @Autowired
    private LifeProductMapper lifeProductMapper;


    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public LifeProduct getByPolicyId(Long id) {
        return lifeProductMapper.getById(id);
    }

    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public Long getIdByCode(String code) {
        Long id=0L;
        if(code!=null){
            id = lifeProductMapper.getIdByCode(code);
        }
        return id;
    }
}

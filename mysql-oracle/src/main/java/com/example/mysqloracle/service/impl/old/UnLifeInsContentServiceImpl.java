package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;

import com.example.mysqloracle.dao.old.UnLifeInsContentMapper;
import com.example.mysqloracle.entity.old.UnLifeInsContent;
import com.example.mysqloracle.service.old.UnLifeInsContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 寿险保单-投保内容信息-子表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Service
public class UnLifeInsContentServiceImpl extends ServiceImpl<UnLifeInsContentMapper, UnLifeInsContent> implements UnLifeInsContentService {


    @Autowired
    private UnLifeInsContentMapper unLifeInsContentMapper;

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public List<UnLifeInsContent> getByPolicyId(Integer policyId) {
        return unLifeInsContentMapper.getByPolicyId(policyId);
    }
}

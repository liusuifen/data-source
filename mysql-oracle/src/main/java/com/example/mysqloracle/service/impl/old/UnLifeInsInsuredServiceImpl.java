package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.old.UnLifeInsInsuredMapper;
import com.example.mysqloracle.entity.old.UnLifeInsInsured;
import com.example.mysqloracle.service.old.UnLifeInsInsuredService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 寿险保单-被保险人信息-子表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Service
public class UnLifeInsInsuredServiceImpl extends ServiceImpl<UnLifeInsInsuredMapper, UnLifeInsInsured> implements UnLifeInsInsuredService {

    @Autowired
    private UnLifeInsInsuredMapper unLifeInsInsuredMapper;

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public List<UnLifeInsInsured> getByPolicyId(Integer policyId) {
        return unLifeInsInsuredMapper.getByPolicyId(policyId);
    }
}

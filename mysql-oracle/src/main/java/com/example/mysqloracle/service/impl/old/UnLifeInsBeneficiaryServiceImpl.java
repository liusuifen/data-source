package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.old.UnLifeInsBeneficiaryMapper;
import com.example.mysqloracle.entity.old.UnLifeInsBeneficiary;
import com.example.mysqloracle.service.old.UnLifeInsBeneficiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 寿险保单-受益人信息-子表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Service
public class UnLifeInsBeneficiaryServiceImpl extends ServiceImpl<UnLifeInsBeneficiaryMapper, UnLifeInsBeneficiary> implements UnLifeInsBeneficiaryService {

    @Autowired
    private UnLifeInsBeneficiaryMapper unLifeInsBeneficiaryMapper;

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public List<UnLifeInsBeneficiary> getByPolicyId(Integer policyId) {
        return unLifeInsBeneficiaryMapper.getByPolicyId(policyId);
    }
}

package com.example.mysqloracle.service.impl.old;



import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;

import com.example.mysqloracle.dao.old.UnLifeInsStateMapper;
import com.example.mysqloracle.entity.old.UnLifeInsState;
import com.example.mysqloracle.service.old.UnLifeInsStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 寿险保单-状态跟踪-影像记录列表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Service
public class UnLifeInsStateServiceImpl extends ServiceImpl<UnLifeInsStateMapper, UnLifeInsState> implements UnLifeInsStateService {

    @Autowired
    private UnLifeInsStateMapper unLifeInsStateMapper;


    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public List<UnLifeInsState> getByPolicyId(Integer policyId) {
        return unLifeInsStateMapper.getByPolicyId(policyId);
    }
}

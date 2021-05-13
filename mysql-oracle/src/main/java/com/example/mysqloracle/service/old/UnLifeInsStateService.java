package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.old.UnLifeInsState;

import java.util.List;

/**
 * <p>
 * 寿险保单-状态跟踪-影像记录列表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
public interface UnLifeInsStateService extends IService<UnLifeInsState> {


    public List<UnLifeInsState> getByPolicyId(Integer policyId);
}

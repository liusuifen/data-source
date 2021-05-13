package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.old.UnLifeInsInsured;

import java.util.List;

/**
 * <p>
 * 寿险保单-被保险人信息-子表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
public interface UnLifeInsInsuredService extends IService<UnLifeInsInsured> {

    List<UnLifeInsInsured> getByPolicyId(Integer policyId);

}

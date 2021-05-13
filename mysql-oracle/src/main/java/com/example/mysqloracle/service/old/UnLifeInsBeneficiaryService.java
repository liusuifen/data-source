package com.example.mysqloracle.service.old;

import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.old.UnLifeInsBeneficiary;

import java.util.List;


/**
 * <p>
 * 寿险保单-受益人信息-子表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
public interface UnLifeInsBeneficiaryService extends IService<UnLifeInsBeneficiary> {

    List<UnLifeInsBeneficiary> getByPolicyId(Integer policyId);

}

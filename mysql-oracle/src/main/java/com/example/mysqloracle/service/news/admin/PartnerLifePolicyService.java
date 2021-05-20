package com.example.mysqloracle.service.news.admin;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.news.admin.PartnerLifePolicy;
import com.example.mysqloracle.param.Param;

/**
 * <p>
 * 寿险保单-投保单主表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-17
 */
public interface PartnerLifePolicyService extends IService<PartnerLifePolicy> {

    CommonResult getAll(Param param);

    CommonResult getError(Param param);

}

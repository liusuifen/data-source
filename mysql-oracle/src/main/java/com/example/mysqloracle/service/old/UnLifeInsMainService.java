package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnLifeInsMain;
import com.example.mysqloracle.param.Param;

import java.util.List;
import java.util.Set;

/**
 * <p>
 * 寿险保单-投保单主表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
public interface UnLifeInsMainService extends IService<UnLifeInsMain> {


    CommonResult getAll(Param param);

    CommonResult getFail(Param param);

    CommonResult changeHesitateDate(Param param);

    CommonResult createCustomer(Param param);

    CommonResult  getTaxType();
}

package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnLifeProductRatio;
import com.example.mysqloracle.param.Param;

import java.util.List;

/**
 * <p>
 * 寿险产品系数 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
public interface UnLifeProductRatioService extends IService<UnLifeProductRatio> {

    /**
     * 获取所以折标数据
     * @param param
     * @return
     */
     CommonResult getRateData(Param param);

    /**
     * 生成模板数据
     * @param param
     * @return
     */
     CommonResult saveTemplate(Param param);
}

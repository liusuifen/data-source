package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnBasicBanner;
import com.example.mysqloracle.param.Param;

/**
 * <p>
 * 渠道商-广告图展示图 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
public interface UnBasicBannerService extends IService<UnBasicBanner> {


    CommonResult getAll(Param param);

}

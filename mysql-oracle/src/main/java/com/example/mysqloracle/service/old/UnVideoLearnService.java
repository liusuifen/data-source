package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnVideoLearn;
import com.example.mysqloracle.param.Param;

/**
 * <p>
 * 培训_视频主表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
public interface UnVideoLearnService extends IService<UnVideoLearn> {


    CommonResult getAllByChannleId(Param param);

    CommonResult getError(Param param);



}

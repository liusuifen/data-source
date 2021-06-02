package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.old.UnVideoCategory;
import com.example.mysqloracle.param.Param;

/**
 * <p>
 * 培训_分类表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
public interface UnVideoCategoryService extends IService<UnVideoCategory> {

    CommonResult getAllCategory(Param param);

}

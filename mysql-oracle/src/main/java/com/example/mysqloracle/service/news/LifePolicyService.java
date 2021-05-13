package com.example.mysqloracle.service.news;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.news.LifePolicy;

/**
 * <p>
 * 寿险保单-投保单主表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
public interface LifePolicyService extends IService<LifePolicy> {


    LifePolicy getById(Long id);

}

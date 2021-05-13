package com.example.mysqloracle.service.news;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.news.LifeProduct;

/**
 * <p>
 * 寿险产品库 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
public interface LifeProductService extends IService<LifeProduct> {

    LifeProduct getByPolicyId(Long id);

    Long getIdByCode(String code);

}

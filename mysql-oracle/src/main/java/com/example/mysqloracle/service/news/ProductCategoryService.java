package com.example.mysqloracle.service.news;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.news.ProductCategory;

/**
 * <p>
 * 产品大类表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
public interface ProductCategoryService extends IService<ProductCategory> {

    ProductCategory getByPolicyId(Long id);

}

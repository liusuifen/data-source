package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.news.ProductCategoryMapper;
import com.example.mysqloracle.entity.news.ProductCategory;
import com.example.mysqloracle.service.news.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 产品大类表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Service
public class ProductCategoryServiceImpl extends ServiceImpl<ProductCategoryMapper, ProductCategory> implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public ProductCategory getByPolicyId(Long id) {
        return productCategoryMapper.getById(id);
    }
}

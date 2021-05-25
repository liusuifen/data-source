package com.example.mysqloracle.dao.news;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifeProductRatioTemplateDetail;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

/**
 * <p>
 * 价值系数模板明细表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
@Mapper
public interface LifeProductRatioTemplateDetailMapper extends BaseMapper<LifeProductRatioTemplateDetail> {


    @Select("select count(*) from life_product_ratio_template_detail where id=#{id}")
    Integer getByTemplateId(@Param("id") Integer id);

}

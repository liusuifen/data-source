package com.example.mysqloracle.dao.news.admin;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.admin.PartnerLifePolicyProduct;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单-险种信息表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-17
 */
@Mapper
public interface PartnerLifePolicyProductMapper extends BaseMapper<PartnerLifePolicyProduct> {

    @Select("select count(*) from partner_life_policy_product where id=#{id}")
    Integer getCount(@Param("id") Long id);




    @Select("select count(*) from partner_life_policy_product where life_policy_id in (select id from partner_life_policy where partner_id=#{partnerId} and LENGTH(id)=5)")
    Integer selectByChannelId(@Param("partnerId") Long partnerId);

}

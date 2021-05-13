package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicyBeneficiary;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 寿险保单受益人表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Mapper
public interface LifePolicyBeneficiaryMapper extends BaseMapper<LifePolicyBeneficiary> {


    @Select("select count(*) from life_policy_beneficiary where life_policy_id=#{lifePolicyId}")
    Integer getCountBypolicyId(@Param("lifePolicyId") Long lifePolicyId);


    @Select("select count(*) from life_policy_beneficiary where id=#{id}")
    Integer getCountById(@Param("id") Long id);

}

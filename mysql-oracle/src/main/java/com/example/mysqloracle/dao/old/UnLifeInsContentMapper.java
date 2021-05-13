package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeInsContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 寿险保单-投保内容信息-子表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Mapper
public interface UnLifeInsContentMapper extends BaseMapper<UnLifeInsContent> {


    @Select("select id, channel_id as channelId, policy_id as policyId, product_id as productId, amount, " +
            "period_guanantee as periodGuanantee, period_payment as periodPayment, fee, ratio, std_premium as stdPremium, " +
            "additional_fee as additionalFee, quantity, is_main as isMain, duty_option as dutyOption, free_option as freeOption, " +
            "loss_rate as lossRate, extend_items as extendItems, state, create_by as createBy, create_time as createTime, " +
            "modify_by as modifyBy, modify_time as modifyTime, delete_time as deleteTime from un_life_ins_content " +
            "where policy_id=#{policyId} ")
    List<UnLifeInsContent> getByPolicyId(@Param("policyId") Integer policyId);



}

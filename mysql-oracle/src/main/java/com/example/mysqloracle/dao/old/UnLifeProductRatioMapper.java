package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnLifeProductRatio;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 寿险产品系数 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-24
 */
@Mapper
public interface UnLifeProductRatioMapper extends BaseMapper<UnLifeProductRatio> {


    @Select("select id, channel_id as channelId, product_id as productId, area, year, year_unit as yearUnit, " +
            "pay_year as payYear, pay_year_unit as payYearUnit, pro_fee as proFee, ratio, renewal, remark, sv_id as svId, " +
            "create_by as createBy, create_time as createTime, modify_by as modifyBy, modify_time as modifyTime, delete_time as deleteTime, state, fyp_rate as fypRate " +
            "from un_life_product_ratio " +
            "where channel_id=#{channelId} " +
            "and delete_time is null")
    List<UnLifeProductRatio> getByChannelId(@Param("channelId") Integer channleId);



    @Select("select id, channel_id as channelId, product_id as productId, area, year, year_unit as yearUnit, " +
            "pay_year as payYear, pay_year_unit as payYearUnit, pro_fee as proFee, ratio, renewal, remark, sv_id as svId, " +
            "create_by as createBy, create_time as createTime, modify_by as modifyBy, modify_time as modifyTime, delete_time as deleteTime, state, fyp_rate as fypRate " +
            "from un_life_product_ratio " +
            "where channel_id=#{channelId} " +
            "and delete_time is null group by product_id")
    List<UnLifeProductRatio> getByGroup(@Param("channelId") Integer channleId);

}

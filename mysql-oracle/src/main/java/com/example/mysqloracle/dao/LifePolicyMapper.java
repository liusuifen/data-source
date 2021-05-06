package com.example.mysqloracle.dao;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.LifePolicy;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 寿险保单-投保单主表 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Mapper
public interface LifePolicyMapper extends BaseMapper<LifePolicy> {

}

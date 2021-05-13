package com.example.mysqloracle.dao.news;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.news.LifePolicy;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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

    @Select("select * from life_policy where id=#{id}")
    LifePolicy getById(@Param("id") Long id);


}

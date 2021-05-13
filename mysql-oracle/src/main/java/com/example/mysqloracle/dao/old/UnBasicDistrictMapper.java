package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnBasicDistrict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 省市区 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Mapper
public interface UnBasicDistrictMapper extends BaseMapper<UnBasicDistrict> {

        @Select("select code from un_basic_district where id =#{id}")
        String getCodeByid(@Param("id") String id );

}

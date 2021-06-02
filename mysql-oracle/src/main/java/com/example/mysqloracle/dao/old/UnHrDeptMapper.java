package com.example.mysqloracle.dao.old;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.entity.old.UnHrDept;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 部门档 Mapper 接口
 * </p>
 *
 * @author blueSky
 * @since 2021-05-27
 */
@Mapper
public interface UnHrDeptMapper extends BaseMapper<UnHrDept> {

    @Select("select   id, pid, channel_id as channelId, dept_name as deptName, state, count_sale as countSale, " +
            "execute_commission as executeCommission, type, code_province as codeProvince, code_city as codeCity, tel, address, " +
            "level, is_law as isLaw, create_by as createBy, create_time as createTime, modify_by as modifyBy, modify_time as modifyTime, did, " +
            "delete_time as deleteTime, code_province_yuebao as codeProvinceYuebao, code_city_yuebao as codeCityYuebao from " +
            "un_hr_dept where id=#{id} ")
    UnHrDept getById(@Param("id") Integer id);

}

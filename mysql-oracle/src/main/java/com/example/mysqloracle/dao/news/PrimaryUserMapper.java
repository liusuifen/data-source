package com.example.mysqloracle.dao.news;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.example.mysqloracle.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface PrimaryUserMapper {

    @Select("select id,USERNAME,PASSWORD,ROLE_ID as roleId,flag from acl_user where id=#{id}")
    User selectById(int id);

}

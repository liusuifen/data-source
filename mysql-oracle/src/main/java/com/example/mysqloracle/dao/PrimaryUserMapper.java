package com.example.mysqloracle.dao;

import com.example.mysqloracle.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface PrimaryUserMapper {

    @Select("select * from acl_user where id=#{id}")
    User selectById(int id);
}

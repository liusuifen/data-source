package com.example.mysqloracle.service.impl;

import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.PrimaryUserMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.pojo.User;
import com.example.mysqloracle.service.ParmaryUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParmaryUserServiceImpl implements ParmaryUserService {

    @Autowired
    PrimaryUserMapper primaryUserMapper;

    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public User sellPrimary() {
        return primaryUserMapper.selectById(1);
    }

    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public User sellSub() {
        System.out.println("执行mapper之前");
        return primaryUserMapper.selectById(1);
    }

    @Override
    public List<User> sellAnd() {
        List<User> list=new ArrayList<>();
        list.add(primaryUserMapper.selectById(1));
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        list.add(primaryUserMapper.selectById(1));
        return list;
    }

}

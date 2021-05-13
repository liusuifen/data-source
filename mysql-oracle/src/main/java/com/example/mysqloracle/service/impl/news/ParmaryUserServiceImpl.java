package com.example.mysqloracle.service.impl.news;

import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.news.PrimaryUserMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.pojo.User;
import com.example.mysqloracle.service.news.ParmaryUserService;
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
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        list.add(primaryUserMapper.selectById(1));
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        list.add(primaryUserMapper.selectById(1));
        return list;
    }

    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    public List<User> getList(){
        List<User> userList=new ArrayList<>();
        for (int i=0;i<4;i++){
            userList.addAll(sellAnd());
        }
        return userList;
    }

}

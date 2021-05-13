package com.example.mysqloracle.service.news;

import com.example.mysqloracle.pojo.User;

import java.util.List;

public interface ParmaryUserService {

     User sellPrimary();

     User sellSub();

     List<User> sellAnd();
}

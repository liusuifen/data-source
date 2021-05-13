package com.example.mysqloracle.service.news;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.news.ServiceUserJson;
import com.example.mysqloracle.entity.news.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
public interface UserService extends IService<User> {

    String getServiceUserJson(Long userId);

    Long getTeamById(Long userId);

}

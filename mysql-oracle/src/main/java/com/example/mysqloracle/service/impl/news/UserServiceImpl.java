package com.example.mysqloracle.service.impl.news;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.news.OrgMapper;
import com.example.mysqloracle.dao.news.SystemRankMapper;
import com.example.mysqloracle.dao.news.UserMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.Org;
import com.example.mysqloracle.entity.news.ServiceUserJson;
import com.example.mysqloracle.entity.news.SystemRank;
import com.example.mysqloracle.entity.news.User;
import com.example.mysqloracle.service.news.UserService;
import com.example.mysqloracle.util.ReflectUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-07
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private SystemRankMapper systemRankMapper;


    @DataSourceSign(ContextConst.DataSourceType.SUB)
    @Override
    public String getServiceUserJson(Long userId) {
        ServiceUserJson serviceUserJson = new ServiceUserJson();
        User user = userMapper.getById(userId);
        serviceUserJson.setId(userId);
        if(ReflectUtil.isNotNull(user)){
            serviceUserJson.setName(user.getRealName());
            serviceUserJson.setOrg_id(user.getOrgId());
            Org org = orgMapper.getById(user.getOrgId());
            if(ReflectUtil.isNotNull(org)){
                serviceUserJson.setOrg_name(org.getName());
                serviceUserJson.setOrg_code(org.getCode());
            }
            serviceUserJson.setTeam_id(user.getTeamId());
            Org team = orgMapper.getById(user.getTeamId());
            if(ReflectUtil.isNotNull(team)){
                serviceUserJson.setTeam_name(team.getName());
                serviceUserJson.setTeam_code(team.getCode());
            }
            serviceUserJson.setRank(user.getJobRank().intValue());
            SystemRank systemRank = systemRankMapper.getById(user.getJobRank());
            if(ReflectUtil.isNotNull(systemRank)){
                serviceUserJson.setRank_name(systemRank.getName());
            }
//            serviceUserJson.setCom_eid();
//            serviceUserJson.setOrg_eid();
        }
        String resultJson = JSONArray.toJSON(serviceUserJson).toString();
        return resultJson;
    }

    @Override
    public Long getTeamById(Long userId) {
        Long teamId=0L;
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        teamId=userMapper.getTeamById(userId);
        return teamId;
    }
}

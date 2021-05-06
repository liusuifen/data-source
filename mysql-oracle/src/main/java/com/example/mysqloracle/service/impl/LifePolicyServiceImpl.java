package com.example.mysqloracle.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.LifePolicyMapper;
import com.example.mysqloracle.entity.LifePolicy;
import com.example.mysqloracle.service.LifePolicyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 寿险保单-投保单主表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Service
public class LifePolicyServiceImpl extends ServiceImpl<LifePolicyMapper, LifePolicy> implements LifePolicyService {

}

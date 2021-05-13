package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.LifePolicyStatusMapper;
import com.example.mysqloracle.entity.news.LifePolicyStatus;
import com.example.mysqloracle.service.news.LifePolicyStatusService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 寿险保单-保单状态变更表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-11
 */
@Service
public class LifePolicyStatusServiceImpl extends ServiceImpl<LifePolicyStatusMapper, LifePolicyStatus> implements LifePolicyStatusService {

}

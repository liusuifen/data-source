package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.MigrationLogMapper;
import com.example.mysqloracle.entity.news.MigrationLog;
import com.example.mysqloracle.service.news.MigrationLogService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 数据迁移日志表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-12
 */
@Service
public class MigrationLogServiceImpl extends ServiceImpl<MigrationLogMapper, MigrationLog> implements MigrationLogService {

}

package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.ContentAdTypeMapper;
import com.example.mysqloracle.entity.news.ContentAdType;
import com.example.mysqloracle.service.news.ContentAdTypeService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容管理-广告位分类 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Service
public class ContentAdTypeServiceImpl extends ServiceImpl<ContentAdTypeMapper, ContentAdType> implements ContentAdTypeService {

}

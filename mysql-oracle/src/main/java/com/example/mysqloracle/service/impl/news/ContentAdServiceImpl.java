package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.ContentAdMapper;
import com.example.mysqloracle.entity.news.ContentAd;
import com.example.mysqloracle.service.news.ContentAdService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容管理-广告位管理 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Service
public class ContentAdServiceImpl extends ServiceImpl<ContentAdMapper, ContentAd> implements ContentAdService {

}

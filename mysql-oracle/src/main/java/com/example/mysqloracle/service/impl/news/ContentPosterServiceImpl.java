package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.ContentPosterMapper;
import com.example.mysqloracle.entity.news.ContentPoster;
import com.example.mysqloracle.service.news.ContentPosterService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 内容管理-海报管理 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Service
public class ContentPosterServiceImpl extends ServiceImpl<ContentPosterMapper, ContentPoster> implements ContentPosterService {

}

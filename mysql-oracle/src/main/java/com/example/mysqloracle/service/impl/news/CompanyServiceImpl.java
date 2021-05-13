package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import com.example.mysqloracle.dao.news.CompanyMapper;
import com.example.mysqloracle.entity.news.Company;
import com.example.mysqloracle.service.news.CompanyService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 保险公司 - 基本信息 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
@Service
public class CompanyServiceImpl extends ServiceImpl<CompanyMapper, Company> implements CompanyService {

}

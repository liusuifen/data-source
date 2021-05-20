package com.example.mysqloracle.service.impl.news;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.CustomerMapper;
import com.example.mysqloracle.entity.news.Customer;
import com.example.mysqloracle.service.news.CustomerService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 客户中心-客户表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-19
 */
@Service
public class CustomerServiceImpl extends ServiceImpl<CustomerMapper, Customer> implements CustomerService {

}

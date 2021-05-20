package com.example.mysqloracle.service.impl.news.admin;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.news.admin.PartnerLifePolicyDocumentMapper;
import com.example.mysqloracle.entity.news.admin.PartnerLifePolicyDocument;
import com.example.mysqloracle.service.news.admin.PartnerLifePolicyDocumentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 寿险保单相关文档表
1.投保人身份证正面
2.投保人身份证反面
3.被保人身份证正面
4.被保人身份证反面
5.被保人户口本
6.被保人出生证
7.银行卡
8.指定受益人身份证明文件正面
9.指定受益人身份证明文件反面
10.银行自动转账授权书
11.投保人人脸识别或拍照
12.被保险人拍照

101.投保人签名
102.投保人拍照
103.被保人签名
104.被保人拍照
105.代理人签名 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-17
 */
@Service
public class PartnerLifePolicyDocumentServiceImpl extends ServiceImpl<PartnerLifePolicyDocumentMapper, PartnerLifePolicyDocument> implements PartnerLifePolicyDocumentService {

}

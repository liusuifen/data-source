package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.old.UnLifeInsContent;

import java.util.List;

/**
 * <p>
 * 寿险保单-投保内容信息-子表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-06
 */
public interface UnLifeInsContentService extends IService<UnLifeInsContent> {

    List<UnLifeInsContent> getByPolicyId(Integer policyId);
}

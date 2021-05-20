package com.example.mysqloracle.service.eums;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.entity.eums.BasicEnum;
import com.example.mysqloracle.entity.eums.BasicEnumVo;

import java.util.List;

/**
 * <p>
 * 枚举管理 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-08
 */
public interface BasicEnumService extends IService<BasicEnum> {

    /**
     * 性别
     * @return
     */
    CommonResult getGender();

    /**
     * 证件类型
     * @return
     */
    CommonResult getIdType();


    /**
     * 婚姻状态
     * @return
     */
    CommonResult getHolderMarriage();


    /**
     * 银行类型
     * @return
     */
    CommonResult getBank();



    /**
     * 国籍类型
     * @return
     */
    CommonResult getNation();

    /**
     * 与保险人关系类型
     * @return
     */
    CommonResult getBeneficiaryIsInsured();

    /**
     * 是否有社保
     * @return
     */
    CommonResult getHasSSid();


    /**
     * 收入来源
     * @return
     */
    CommonResult getSourceFrom();

    /**
     * 税收类型
     * @return
     */
    CommonResult getTaxType();


    /**
     * 税收类型
     * @return
     */
    CommonResult getEarningsType();




    String getNewEnum(String id);

}

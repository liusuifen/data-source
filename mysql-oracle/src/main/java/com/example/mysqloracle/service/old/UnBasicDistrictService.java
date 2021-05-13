package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.old.UnBasicDistrict;

/**
 * <p>
 * 省市区 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
public interface UnBasicDistrictService extends IService<UnBasicDistrict> {


    String getCodeById(String id);

}

package com.example.mysqloracle.service;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.entity.UnLifeInsMain;

import java.util.List;

/**
 * <p>
 * 寿险保单-投保单主表 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
public interface UnLifeInsMainService extends IService<UnLifeInsMain> {


    List<UnLifeInsMain> getAll();
}

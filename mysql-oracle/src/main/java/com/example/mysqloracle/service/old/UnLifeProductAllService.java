package com.example.mysqloracle.service.old;


import com.baomidou.mybatisplus.service.IService;
import com.example.mysqloracle.dao.old.UnLifeProductAllMapper;
import com.example.mysqloracle.entity.old.UnLifeProductAll;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 寿险总档 服务类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
public interface UnLifeProductAllService extends IService<UnLifeProductAll> {

        String getCodeById(Integer id);

}

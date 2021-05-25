package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.dao.old.UnLifeProductAllMapper;
import com.example.mysqloracle.entity.old.UnLifeProductAll;
import com.example.mysqloracle.service.old.UnLifeProductAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 寿险总档 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Service
public class UnLifeProductAllServiceImpl extends ServiceImpl<UnLifeProductAllMapper, UnLifeProductAll> implements UnLifeProductAllService {


    @Autowired
    private  UnLifeProductAllMapper unLifeProductAllMapper;

    @Override
    public String getCodeById(Integer id) {
        String code="";
        if(id!=0){
            code = unLifeProductAllMapper.getCodeById(id);
        }
        return code;
    }
}

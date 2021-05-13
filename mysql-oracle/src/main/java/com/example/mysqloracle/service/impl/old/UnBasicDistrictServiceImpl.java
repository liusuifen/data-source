package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.old.UnBasicDistrictMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.old.UnBasicDistrict;
import com.example.mysqloracle.service.old.UnBasicDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 省市区 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-10
 */
@Service
public class UnBasicDistrictServiceImpl extends ServiceImpl<UnBasicDistrictMapper, UnBasicDistrict> implements UnBasicDistrictService {

    @Autowired
    private UnBasicDistrictMapper unBasicDistrictMapper;

    @Override
    public String getCodeById(String id) {
        String code="0";
        if(id!=null && !"".equals(id)){
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
             code = unBasicDistrictMapper.getCodeByid(id);
        }
        return code;
    }
}

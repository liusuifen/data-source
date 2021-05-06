package com.example.mysqloracle.service.impl;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.common.DataSourceSign;
import com.example.mysqloracle.dao.LifePolicyMapper;
import com.example.mysqloracle.dao.UnHrRankMapper;
import com.example.mysqloracle.dao.UnLifeInsMainMapper;
import com.example.mysqloracle.entity.LifePolicy;
import com.example.mysqloracle.entity.UnLifeInsMain;
import com.example.mysqloracle.service.UnLifeInsMainService;
import com.example.mysqloracle.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * <p>
 * 寿险保单-投保单主表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-04-29
 */
@Service
@Slf4j
public class UnLifeInsMainServiceImpl extends ServiceImpl<UnLifeInsMainMapper, UnLifeInsMain> implements UnLifeInsMainService {

    @Autowired
    private  UnLifeInsMainMapper unLifeInsMainMapper;

    @Autowired
    private UnHrRankMapper unHrRankMapper;

    @Autowired
    private LifePolicyMapper lifePolicyMapper;
    /**
     * 获取老系统un_life_ins_main 表数据
     * @return
     */
    @DataSourceSign(ContextConst.DataSourceType.PRIMARY)
    @Override
    public List<UnLifeInsMain> getAll() {
        List<UnLifeInsMain> all = unLifeInsMainMapper.getAll();
        return all;
    }

    /**
     * 把数据保存入新系统合作方数据库中
     */
    @DataSourceSign(ContextConst.DataSourceType.SUB)
    public void saveNewPartner(UnLifeInsMain unLifeInsMain){

        LifePolicy lifePolicy = lifePolicyMapper.selectById(unLifeInsMain.getId());
        if(ReflectUtil.isNull(lifePolicy)){
            LifePolicy policy = new LifePolicy();
            policy.setId(new Long(unLifeInsMain.getId()));
            policy.setOrderNo(unLifeInsMain.getOrderNo());
            policy.setPolicyNo(unLifeInsMain.getPolicyNo());
            policy.setPolicySn(unLifeInsMain.getPolicySn());

            if(!"0".equals(unLifeInsMain.getRankId())){
                policy.setSalesUserRankId(Long.valueOf(unLifeInsMain.getRankId()));
            }else{

            }

            policy.setDateStart(LocalDate.parse(unLifeInsMain.getValDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            policy.setIsLegalBeneficiary(unLifeInsMain.getIsLegalBenefic());
            policy.setFee(unLifeInsMain.getTotal());
            policy.setStatus(unLifeInsMain.getState());//枚举类型不一致
            policy.setPayStatus(unLifeInsMain.getPayStatus());//枚举类型不一致

        }else{


        }
    }

//    @DataSourceSign(ContextConst.DataSourceType.SUB)
//    public String oldRankId(String rankId){
//        unHrRankMapper.selectById()
//    }


}

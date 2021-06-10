package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.ContentAdMapper;
import com.example.mysqloracle.dao.news.ContentPosterMapper;
import com.example.mysqloracle.dao.news.MigrationLogMapper;
import com.example.mysqloracle.dao.news.UserMapper;
import com.example.mysqloracle.dao.old.UnBasicBannerMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.*;
import com.example.mysqloracle.entity.old.UnBasicBanner;
import com.example.mysqloracle.enums.BannerEnum;
import com.example.mysqloracle.enums.MigrationStatusEnum;
import com.example.mysqloracle.enums.MigrationTypeEnum;
import com.example.mysqloracle.enums.PartnerEnum;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.old.UnBasicBannerService;
import com.example.mysqloracle.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 渠道商-广告图展示图 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-06-08
 */
@Service
@Slf4j
public class UnBasicBannerServiceImpl extends ServiceImpl<UnBasicBannerMapper, UnBasicBanner> implements UnBasicBannerService {


    @Autowired
    private UnBasicBannerMapper unBasicBannerMapper;

    @Autowired
    private MigrationLogMapper migrationLogMapper;

    @Autowired
    private ContentAdMapper contentAdMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContentPosterMapper contentPosterMapper;

    @Override
    public CommonResult getAll(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnBasicBanner> unBasicBannersList = unBasicBannerMapper.selectByChannelId(param.getChannelId());
        Map<String, List<UnBasicBanner>> mapList = new HashMap<String, List<UnBasicBanner>>();
        mapList = unBasicBannersList.stream().collect(Collectors.groupingBy(unBasicBanner -> unBasicBanner.getLocation()));
        Integer sort = 1;
        for (Map.Entry<String, List<UnBasicBanner>> entry : mapList.entrySet()) {
//            try {
            syncUnbannerTemple(entry.getKey(), entry.getValue(), sort, param);
            sort++;
//            }catch (Exception e){
//                insertMigrationLog(), MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(),param);
//            }
        }

        return new CommonResult("海报数据同步至新系统合作方成功");
    }

    public void syncUnbannerTemple(String key, List<UnBasicBanner> list, Integer sort, Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Long userId = userMapper.getByRoleType(1);//超级管理员id
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        ContentPoster contentPoster = contentPosterMapper.getByName(BannerEnum.getNameByCode(key));
        if (ReflectUtil.isNull(contentPoster)) {
            contentPoster = new ContentPoster();
            if (BannerEnum.Banner_产品.getCode().equals(key)) {
                contentPoster.setName("产品");
                contentPoster.setIsFestival(1);
            } else if (BannerEnum.Banner_理念.getCode().equals(key)) {
                contentPoster.setName("理念");
                contentPoster.setIsFestival(0);
            } else if (BannerEnum.Banner_增员.getCode().equals(key)) {
                contentPoster.setName("增员");
                contentPoster.setIsFestival(0);
            } else if (BannerEnum.Banner_生日.getCode().equals(key)) {
                contentPoster.setName("生日");
                contentPoster.setIsFestival(1);
            } else if (BannerEnum.Banner_每日一课.getCode().equals(key)) {
                contentPoster.setName("每日一课");
                contentPoster.setIsFestival(0);
            } else if (BannerEnum.Banner_弹窗宣传图.getCode().equals(key)) {
                contentPoster.setName("弹窗宣传图");
                contentPoster.setIsFestival(0);
            } else if (BannerEnum.Banner_节日.getCode().equals(key)) {
                contentPoster.setName("节日");
                contentPoster.setIsFestival(1);
            } else if (BannerEnum.Banner_节气.getCode().equals(key)) {
                contentPoster.setName("节气");
                contentPoster.setIsFestival(0);
            } else if (BannerEnum.Banner_获客.getCode().equals(key)) {
                contentPoster.setName("获客");
                contentPoster.setIsFestival(0);
            } else if (BannerEnum.Banner_顶图banner.getCode().equals(key)) {
                for (UnBasicBanner unBasicBanner : list) {
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    ContentAd contentAd = contentAdMapper.getById(intToLong(unBasicBanner.getId()));
                    if (ReflectUtil.isNull(contentAd)) {
                        contentAd = new ContentAd();
                        contentAd.setContentAdTypeId(1L);
                        if (userId == null) {
                            contentAd.setUserId(0L);
                        } else {
                            contentAd.setUserId(userId);
                        }
                        contentAd.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unBasicBanner.getModifyTime())));
                        contentAd.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unBasicBanner.getCreateTime())));
                        contentAd.setId(intToLong(unBasicBanner.getId()));
                        contentAd.setIsDeleted(0);//默认为未删除
                        contentAd.setDescription(unBasicBanner.getImgName());
                        contentAd.setPublishEnd(DateUtil.strToLocalDate("2021-12-31"));
                        contentAd.setPublishStart(DateUtil.strToLocalDate("2021-01-01"));
                        String value = ProPertiesUtil.getValue("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\application.properties", "oss.url");
                        contentAd.setPic(value+unBasicBanner.getImage());
                        contentAd.setUrl(unBasicBanner.getLink());
                        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                        contentAdMapper.insert(contentAd);
                        log.info("主banner图数据迁移成功，数据id：{}", unBasicBanner.getId());
                    } else {
                        log.info("主banner图数据已迁移，数据id：{},忽略", unBasicBanner.getId());
                    }
                }
            }
            if (!"BAB0003".equals(key)) {
                List<Img> imgList = new ArrayList<>();
                String value = ProPertiesUtil.getValue("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\application.properties", "oss.url");
                for (UnBasicBanner unBasicBanner : list) {
                    Img img = new Img();
                    img.setId(IdUtil.generateId());
                    img.setUrl(value+unBasicBanner.getImage());
                    imgList.add(img);
                }
                String item = JsonUtils.objectToJson(imgList);
                contentPoster.setItem(item);
                contentPoster.setIsDeleted(0);
                contentPoster.setCreatedAt(LocalDateTime.now());
                contentPoster.setStatus(1);//默认为有效
                contentPoster.setSort(sort);
                contentPoster.setUpdatedAt(LocalDateTime.now());
                if (userId == null) {
                    contentPoster.setUserId(0L);
                } else {
                    contentPoster.setUserId(userId);
                }
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                contentPosterMapper.insert(contentPoster);
                log.info("该海报数据迁移成功，海报id:{}", contentPoster.getId());
//            insertMigrationLog(contentPoster.getId(),MigrationStatusEnum.MIGRATION_STATUS_SUCCESS.getCode(),param);
            }
        } else {
            log.info("该海报数据已经迁移过，海报id:{}，忽略", contentPoster.getId());
        }

    }


    public void insertMigrationLog(Long id, Integer status, Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        MigrationLog log = migrationLogMapper.getByOldIdAndType(id);
        if (ReflectUtil.isNull(log)) {
            log = new MigrationLog();
            log.setId(IdUtil.generateId());
            log.setOldId(id);
            log.setType(MigrationTypeEnum.MIGRATION_TYPE_UNBANNER.getCode());
            log.setSourcePartnerId(intToLong(param.getChannelId()));
            log.setStatus(status);
            log.setCreatedAt(LocalDateTime.now());
            log.setUpdatedAt(LocalDateTime.now());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.insert(log);
        } else {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.updateAt(LocalDateTime.now(), status, id, MigrationTypeEnum.MIGRATION_TYPE_UNBANNER.getCode());
        }
    }

    /**
     * Int类型转换为Long
     *
     * @param value
     * @return
     */
    public Long intToLong(Integer value) {
        Long result = new Long(0);
        if (value != null) {
            result = new Long(value);
        }
        return result;
    }

}

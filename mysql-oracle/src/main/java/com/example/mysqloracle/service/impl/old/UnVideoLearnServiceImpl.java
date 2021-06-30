package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.*;
import com.example.mysqloracle.dao.old.UnVideoLearnCollectMapper;
import com.example.mysqloracle.dao.old.UnVideoLearnMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.ContentCourse;
import com.example.mysqloracle.entity.news.ContentCourseInfo;
import com.example.mysqloracle.entity.news.MigrationLog;
import com.example.mysqloracle.entity.news.UserFavorite;
import com.example.mysqloracle.entity.old.UnVideoLearn;
import com.example.mysqloracle.entity.old.UnVideoLearnCollect;
import com.example.mysqloracle.enums.MigrationStatusEnum;
import com.example.mysqloracle.enums.MigrationTypeEnum;
import com.example.mysqloracle.enums.PartnerNameEnum;
import com.example.mysqloracle.enums.UpLoadTypeEnum;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.old.UnVideoLearnService;
import com.example.mysqloracle.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.unit.DataUnit;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 培训_视频主表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Service
@Slf4j
public class UnVideoLearnServiceImpl extends ServiceImpl<UnVideoLearnMapper, UnVideoLearn> implements UnVideoLearnService {

    @Autowired
    private UnVideoLearnMapper unVideoLearnMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private SystemRankMapper systemRankMapper;

    @Autowired
    private ContentCourseMapper contentCourseMapper;

    @Autowired
    private MigrationLogMapper migrationLogMapper;

    @Autowired
    private UserFavoriteMapper userFavoriteMapper;

    @Autowired
    private ContentCourseTypeMapper contentCourseTypeMapper;

    @Autowired
    private UnVideoLearnCollectMapper  unVideoLearnCollectMapper;


    @Autowired
    private ContentCourseInfoMapper contentCourseInfoMapper;

    @Override
    public CommonResult getAllByChannleId(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnVideoLearn> unVideLearnLists = unVideoLearnMapper.getByChannleId(param.getChannelId());
        if (!CollectionUtils.isEmpty(unVideLearnLists)) {
            for (UnVideoLearn unVideLearn : unVideLearnLists) {
//                try {
                    saveContent(unVideLearn, param);
//                } catch (Exception e) {
//                    insertMigrationLog(intToLong(unVideLearn.getId()), MigrationStatusEnum.MIGRATION_STATUS_FAIL.getCode(),param);
//                }
            }
            moveFavorite(param);
        }
        return new CommonResult("课程数据迁移完成！");
    }

    @Override
    public CommonResult getError(Param param) {

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        List<Integer> errorCourseList = migrationLogMapper.getErrorCourse(intToLong(param.getChannelId()));
        if(!CollectionUtils.isEmpty(errorCourseList)){
            for (Integer courseId : errorCourseList) {
                UnVideoLearn unVideoLearn = unVideoLearnMapper.getById(courseId);
                if(ReflectUtil.isNotNull(unVideoLearn)){
                    saveContent(unVideoLearn, param);
                }
            }
        }

        return null;
    }

    /**
     *
     * @param unVideoLearn
     * @param param
     */
    public void saveContent(UnVideoLearn unVideoLearn, Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer num = contentCourseMapper.getCountById(intToLong(unVideoLearn.getId()));
        if (num == 0) {
            ContentCourse contentCourse = new ContentCourse();
            contentCourse.setId(intToLong(unVideoLearn.getId()));
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
            String categoryName = unVideoLearnMapper.getCategoryName(unVideoLearn.getType(), param.getChannelId());
            if(categoryName!=null){
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                Integer categoryId = contentCourseTypeMapper.getByCategoryName(categoryName);
                if(categoryId!=null){
                    contentCourse.setContentCourseTypeId(intToLong(categoryId));
                }
            }else {
                contentCourse.setContentCourseTypeId(intToLong(unVideoLearn.getType()));
            }
            String value = ProPertiesUtil.getValue("C:\\Users\\bl007\\IdeaProjects\\data-source\\mysql-oracle\\src\\main\\resources\\application.properties", "oss.url");
            contentCourse.setCover(value+unVideoLearn.getImg());
            contentCourse.setTitle(unVideoLearn.getTitle());
            contentCourse.setAuthor(PartnerNameEnum.getPartnerNameByChannelId(param.getChannelId()));//填写总公司名称
            contentCourse.setIntroduction(unVideoLearn.getIntroduction());
            String orgJoin = "[]";
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            List<Long> orgId = orgMapper.getOrg();
            if (!CollectionUtils.isEmpty(orgId)) {
                orgJoin = String.join(",", orgId.toString());
            }
            contentCourse.setReceiveOrg(orgJoin);
            contentCourse.setParentOrg("[]");
            contentCourse.setTotalOrg(orgJoin);
            contentCourse.setIsShare(unVideoLearn.getIsPublic());//默认为1：可分享
            String rankJoin = "[]";
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            List<Long> rankId = systemRankMapper.getAllID();
            if (!CollectionUtils.isEmpty(rankId)) {
                rankJoin = String.join(",", rankId.toString());
            }
            contentCourse.setReceiveRank(rankJoin);
            contentCourse.setIsLearn(0);//默认为1：可学
            contentCourse.setReceiveChannel("[]");//默认为空
            contentCourse.setIsVisible(0);//默认为1：是
            if(unVideoLearn.getState()==1){
                contentCourse.setStatus(1);
            }else {
                contentCourse.setStatus(2);
            }
            if(!"".equals(unVideoLearn.getDate())){
                contentCourse.setAttendanceTime(DateUtil.strToLocalDate(unVideoLearn.getDate()));
            }
            contentCourse.setClockInHour(0);
            contentCourse.setClockInMinute(unVideoLearn.getEffectiveTime());
            contentCourse.setClockInSecond(0);
            contentCourse.setPlatformOriented("[1]");//默认1-app
            contentCourse.setUploadType(UpLoadTypeEnum.getNewByOld(unVideoLearn.getMediaType()));
            if(unVideoLearn.getDesc()!=null && !"".equals(unVideoLearn.getDesc())){
                contentCourse.setUploadType(UpLoadTypeEnum.UP_LOAD_TYPE_无媒体.getNews());
            }
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Long userId = userMapper.getByRoleType(1);//超级管理员id
            if (userId == null) {
                contentCourse.setCreatedUserId(0L);
            } else {
                contentCourse.setCreatedUserId(userId);
            }
//        contentCourse.setPublishTime();//默认为空
            contentCourse.setUserId(intToLong(unVideoLearn.getCreateBy()));
            contentCourse.setView(intToLong(100));//默认为100
            contentCourse.setIsDeleted(0);//默认未删除
            contentCourse.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoLearn.getCreateTime())));
            contentCourse.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoLearn.getModifyTime())));
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            contentCourseMapper.insert(contentCourse);
            log.info("新系统内容管理-课程表数据迁移成功，课程id{}", contentCourse.getId());
        } else {
            log.info("新系统内容管理-课程表数据已存在，忽略！");
        }

        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer infoNum = contentCourseInfoMapper.getCountById(intToLong(unVideoLearn.getId()));
        if (infoNum == 0) {
            ContentCourseInfo contentCourseInfo = new ContentCourseInfo();

            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            ContentCourse contentCourse = contentCourseMapper.getById(intToLong(unVideoLearn.getId()));
            if (ReflectUtil.isNotNull(contentCourse)) {
                contentCourseInfo.setContentCourseId(contentCourse.getId());
                if(contentCourse.getUploadType()==UpLoadTypeEnum.UP_LOAD_TYPE_无媒体.getNews()){
                    contentCourseInfo.setRemark(HtmlUtil.replaceHtmlTag(unVideoLearn.getDesc(),"img", "src", "src=\"https://pro-unions.oss-cn-shenzhen.aliyuncs.com", "\""));
                }else {
                    contentCourseInfo.setRemark("");
                }
            }
            contentCourseInfo.setUrl(unVideoLearn.getMediaUrl());
            contentCourseInfo.setLinkUrl("");
            contentCourseInfo.setFileName("");
            contentCourseInfo.setUserId(intToLong(unVideoLearn.getCreateBy()));
            contentCourseInfo.setIsDeleted(0);//未删除
            contentCourseInfo.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoLearn.getCreateTime())));
            contentCourseInfo.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoLearn.getModifyTime())));
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            contentCourseInfoMapper.insert(contentCourseInfo);
            log.info("新系统内容管理-课程信息表数据迁移成功，课程关联id{}", contentCourseInfo.getContentCourseId());
            insertMigrationLog(intToLong(unVideoLearn.getId()), MigrationStatusEnum.MIGRATION_STATUS_SUCCESS.getCode(),param);
        } else {
            log.info("新系统内容管理-课程信息表数据已存在，忽略！");
        }
    }

    public void moveFavorite(Param param){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnVideoLearnCollect> unVideoLearnCollects = unVideoLearnCollectMapper.selectByChannelId(param.getChannelId());
        if(!CollectionUtils.isEmpty(unVideoLearnCollects)){
            for (UnVideoLearnCollect unVideoLearnCollect : unVideoLearnCollects) {
                DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                Integer count = userFavoriteMapper.getCountById(unVideoLearnCollect.getId());
                if(count==0){
                    UserFavorite userFavorite = new UserFavorite();
                    userFavorite.setId(unVideoLearnCollect.getId());
                    userFavorite.setType(2);//课程
                    userFavorite.setUserId(intToLong(unVideoLearnCollect.getUserId()));
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
                    UnVideoLearn learn = unVideoLearnMapper.getById(unVideoLearnCollect.getVideoId());
                    if(ReflectUtil.isNotNull(learn)){
                        userFavorite.setTitle(learn.getTitle());
                    }
                    userFavorite.setContentId(intToLong(unVideoLearnCollect.getVideoId()));
                    userFavorite.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoLearnCollect.getCreateTime())));
                    DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
                    userFavoriteMapper.insert(userFavorite);
                    log.info("新系统内容管理-收藏信息表数据迁移成功");
                }else {
                    log.info("新系统内容管理-收藏信息表数据已迁移，忽略");
                }
            }
        }else {
            log.info("老系统课程用户收藏数据不存在，忽略！");
        }
    }



    public void insertMigrationLog(Long id, Integer status, Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        MigrationLog log = migrationLogMapper.getByOldId(id, MigrationTypeEnum.MIGRATION_TYPE_COURSE.getCode());
        if (ReflectUtil.isNull(log)) {
            log = new MigrationLog();
            log.setId(IdUtil.generateId());
            log.setOldId(id);
            log.setType(MigrationTypeEnum.MIGRATION_TYPE_COURSE.getCode());
            log.setSourcePartnerId(intToLong(param.getChannelId()));
            log.setStatus(status);
            log.setCreatedAt(LocalDateTime.now());
            log.setUpdatedAt(LocalDateTime.now());
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.insert(log);
        } else {
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            migrationLogMapper.updateAt(LocalDateTime.now(), status, id, MigrationTypeEnum.MIGRATION_TYPE_COURSE.getCode());
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

    public static void main(String[] args) {
        List<Long> a = new ArrayList<>();
        a.add(new Long(12));
        a.add(new Long(23));
        String join = String.join(",", a.toString());
        System.out.println(join);
    }


}

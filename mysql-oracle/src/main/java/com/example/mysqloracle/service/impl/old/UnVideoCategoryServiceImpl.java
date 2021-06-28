package com.example.mysqloracle.service.impl.old;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.example.mysqloracle.common.CommonResult;
import com.example.mysqloracle.common.ContextConst;
import com.example.mysqloracle.dao.news.ContentCourseTypeMapper;
import com.example.mysqloracle.dao.news.UserMapper;
import com.example.mysqloracle.dao.old.UnVideoCategoryMapper;
import com.example.mysqloracle.datasource.DataSourceContextHolder;
import com.example.mysqloracle.entity.news.ContentCourseType;
import com.example.mysqloracle.entity.old.UnVideoCategory;
import com.example.mysqloracle.param.Param;
import com.example.mysqloracle.service.old.UnVideoCategoryService;
import com.example.mysqloracle.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 * 培训_分类表 服务实现类
 * </p>
 *
 * @author blueSky
 * @since 2021-05-28
 */
@Service
@Slf4j
public class UnVideoCategoryServiceImpl extends ServiceImpl<UnVideoCategoryMapper, UnVideoCategory> implements UnVideoCategoryService {

    @Autowired
    private UnVideoCategoryMapper unVideoCategoryMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ContentCourseTypeMapper contentCourseTypeMapper;


    @Override
    public CommonResult getAllCategory(Param param) {
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.PRIMARY.toString());
        List<UnVideoCategory> allCategory = unVideoCategoryMapper.getAllCategory(param.getChannelId());
        if(!CollectionUtils.isEmpty(allCategory)){
            for (UnVideoCategory unVideoCategory : allCategory) {
//                try {
                    saveCateGory(unVideoCategory);
//                }catch (Exception e){
//
//                }
            }
        }

        return new CommonResult("课程分类数据迁移成功");
    }


    public void saveCateGory(UnVideoCategory unVideoCategory){
        DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
        Integer num = contentCourseTypeMapper.getByName(unVideoCategory.getName());
        if(num==0){
            ContentCourseType contentCourseType = new ContentCourseType();
            contentCourseType.setId(intToLong(unVideoCategory.getId()));
            contentCourseType.setName(unVideoCategory.getName());
            contentCourseType.setSort(unVideoCategory.getSort());
            if(unVideoCategory.getState()==0){
                contentCourseType.setStatus(2);//禁用
            }else {
                contentCourseType.setStatus(unVideoCategory.getState());
            }

            if("每日一课".equals(unVideoCategory.getName())){
                contentCourseType.setIsLesson(1);
            }else {
                contentCourseType.setIsLesson(0);
            }
            contentCourseType.setIsDeleted(0);
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            Long userId = userMapper.getByRoleType(1);//超级管理员id
            if(userId==null){
                contentCourseType.setUserId(0L);
            }else {
                contentCourseType.setUserId(userId);
            }
            contentCourseType.setCreatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoCategory.getCreateTime())));
            contentCourseType.setUpdatedAt(DateUtil.convertTimeToLocalDateTime(intToLong(unVideoCategory.getModifyTime())));
            DataSourceContextHolder.setDataSource(ContextConst.DataSourceType.SUB.toString());
            contentCourseTypeMapper.insert(contentCourseType);
            log.info("新系统课程类型数据迁移成功，课程id{}", unVideoCategory.getId());
        }else {
            log.info("新系统课程类型数据迁移已存在，课程id{}，忽略！", unVideoCategory.getId());
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

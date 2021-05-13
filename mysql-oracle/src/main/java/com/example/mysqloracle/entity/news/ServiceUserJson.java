package com.example.mysqloracle.entity.news;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author blueSky
 * @Description:产品大类表
 * @since 2021-05-07
 */
@ApiModel(value = "服务代理人")
@Data
@EqualsAndHashCode(callSuper = false)
public class ServiceUserJson implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private Integer rank;

    private Long org_id;

    private String com_eid;

    private String org_eid;

    private Long team_id;

    private String org_code;

    private String org_name;

    private String rank_name;

    private String team_code;

    private String team_name;

    private Integer is_partner;

}
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
@ApiModel(value = "产品大类表")
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("product_category")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    @TableField("name")
    private String name;
    @TableField("status")
    private Integer status;


    public static final String ID = "id";

    public static final String NAME = "name";

    public static final String STATUS = "status";

}
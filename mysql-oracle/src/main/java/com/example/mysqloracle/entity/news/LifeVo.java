package com.example.mysqloracle.entity.news;


import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author blueSky
 * @Description:寿险保单-投保单主表
 * @since 2021-04-29
 */
@Data
public class LifeVo implements Serializable {

    private LocalDate returnDate;

    private LocalDate validHesitateDate;

    private Long id;

    private Integer isAfterHesitate;


    private Integer isChannel;

    private LocalDate dateAccept;

    private String mainProductCode;


}
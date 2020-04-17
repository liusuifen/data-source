package com.example.mysqloracle.pojo;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

@Data
@TableName("acl_user")
public class User {

    @TableId
    @TableField
    private Integer id;
    @TableField("USERNAME")
    private String username;
    @TableField("PASSWORD")
    private String password;
    @TableField("ROLE_ID")
    private Integer roleId;

}

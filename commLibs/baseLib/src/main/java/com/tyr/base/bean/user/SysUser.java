package com.tyr.base.bean.user;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@TableName("sys_user")
public class SysUser extends Model<SysUser> {

    @TableId
    private long id;
    private String username;
    private String password;
    @TableField(exist = false)
    private List<SysRole> roles;
}

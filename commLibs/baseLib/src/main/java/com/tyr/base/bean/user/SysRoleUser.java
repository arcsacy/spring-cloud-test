package com.tyr.base.bean.user;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@TableName("sys_role_user")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class SysRoleUser extends Model<SysRoleUser> {

    @TableId
    private long id;
    private long userId;
    private long roleId;


}

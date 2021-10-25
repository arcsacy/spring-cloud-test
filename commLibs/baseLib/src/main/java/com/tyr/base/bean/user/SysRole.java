package com.tyr.base.bean.user;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@TableName("sys_role")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class SysRole extends Model<SysRole> {


    @TableId
    private long id;
    private String name;


}

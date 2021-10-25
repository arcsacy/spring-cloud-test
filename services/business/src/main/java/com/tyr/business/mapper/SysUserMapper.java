package com.tyr.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.tyr.base.bean.user.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * @Class : SysUserMapper
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/13 12:30
 * @Version : 1.0
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findLoginUser(@Param("username") String username);

}

package com.tyr.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tyr.base.bean.user.SysUser;


/**
 * @Class : SysUserService
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/13 12:29
 * @Version : 1.0
 */
public interface SysUserService extends IService<SysUser> {

    SysUser findByUserId(long userId);

    SysUser findByUsername(String username);
}

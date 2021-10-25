package com.tyr.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tyr.base.bean.user.SysUser;
import com.tyr.business.mapper.SysUserMapper;
import com.tyr.business.service.SysUserService;
import org.springframework.stereotype.Service;

/**
 * @Class : SysUserServiceImpl
 * @Description : TODO
 * @Author : Tyrance
 * @Date : 2021/10/13 12:31
 * @Version : 1.0
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Override
    public SysUser findByUserId(long userId) {
        return getBaseMapper().selectById(userId);
    }

    @Override
    public SysUser findByUsername(String username) {
        return getBaseMapper().findLoginUser(username);
    }
}

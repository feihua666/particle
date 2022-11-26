package com.particle.func.adapter.login;

import com.particle.func.infrastructure.dos.FuncDO;

import java.util.List;

/**
 * 获取用户的功能，获取当前登录用户的功能
 * Created by yangwei
 * Created at 2020/12/14 17:33
 */
public interface FuncLoginService {

    /**
     * 获取用户权限信息
     * @param userId
     * @return
     */
    List<FuncDO> retrieveFuncsByUserId(Long userId);
}

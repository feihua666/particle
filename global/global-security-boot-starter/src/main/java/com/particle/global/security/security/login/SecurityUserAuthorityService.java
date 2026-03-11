package com.particle.global.security.security.login;

import com.particle.global.dto.login.LoginUser;
import com.particle.global.dto.login.UserGrantedAuthority;

import java.util.List;

/**
 * 用户权限服务
 * 该服务是可选的
 * 在用户登录时获取用户权限信息
 * Created by yangwei
 * Created at 2020/12/11 18:10
 */
public interface SecurityUserAuthorityService {

    /**
     * 获取用户权限信息
     * @param loginUser
     * @return
     */
    List<UserGrantedAuthority> retrieveUserAuthoritiesByUserId(LoginUser loginUser);
}

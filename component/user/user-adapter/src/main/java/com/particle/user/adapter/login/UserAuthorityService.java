package com.particle.user.adapter.login;

import java.util.List;

/**
 * 用户权限服务
 * Created by yangwei
 * Created at 2020/12/11 18:10
 */
public interface UserAuthorityService {

    /**
     * 获取用户权限信息
     * @param userId
     * @return
     */
    List<String> retrieveUserAuthoritiesByUserId(Long userId);
}

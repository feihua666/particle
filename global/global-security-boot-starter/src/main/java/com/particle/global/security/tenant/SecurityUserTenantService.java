package com.particle.global.security.tenant;

import com.particle.global.dto.login.GrantedTenant;

import java.util.List;

/**
 * 租户服务
 * Created by yangwei
 * Created at 2023-04-14 11:34:56
 */
public interface SecurityUserTenantService {

    /**
     * 获取租户信息
     * @param userId
     * @return
     */
    List<GrantedTenant> retrieveUserTenantByUserId(Long userId);
}

package com.particle.global.security.tenant;

import java.util.List;

/**
 * 租户服务
 * Created by yangwei
 * Created at 2023-04-14 11:34:56
 */
public interface UserTenantService {

    /**
     * 获取租户信息
     * @param userId
     * @param limitedTenantId 限制的租户
     * @return
     */
    List<GrantedTenant> retrieveUserTenantByUserId(Long userId,Long limitedTenantId);
}

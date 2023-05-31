package com.particle.global.security.security.login;

import com.particle.global.security.tenant.GrantedTenant;

import java.util.List;

/**
 * 部门服务
 * Created by yangwei
 * Created at 2023-05-30 16:30:35
 */
public interface UserDeptService {

    /**
     * 获取部门信息
     * @param userId
     * @return
     */
    DeptInfo retrieveUserDeptInfoByUserId(Long userId);
}

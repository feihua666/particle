package com.particle.global.security.security.login;

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

    /**
     * 获取部门信息
     * 注意：调用该方法是获取当前登录用户的部门信息，用以全局保存当前登录用户的设置的部门数据权限数据，以便在用户登录期间根据已授权的部门信息获取用户其它的数据权限
     * @return
     */
    List<DeptInfo> retrieveDeptInfoByDataConstraint();
}

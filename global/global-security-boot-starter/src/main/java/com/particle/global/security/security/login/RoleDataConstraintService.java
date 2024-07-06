package com.particle.global.security.security.login;

import java.util.List;

/**
 * <p>
 * 角色数据范围约束服务
 * </p>
 *
 * @author yangwei
 * @since 2024/7/5 12:01
 */
public interface RoleDataConstraintService {

    /**
     * 根据角色获取角色已配置的数据范围约束
     * @param roleId
     * @return
     */
    List<GrantedDataConstraint> retrieveRoleDataConstraintByRoleId(Long roleId);
}

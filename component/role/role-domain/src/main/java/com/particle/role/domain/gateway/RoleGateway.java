package com.particle.role.domain.gateway;

import com.particle.common.domain.gateway.IBaseGateway;
import com.particle.role.domain.Role;
import com.particle.role.domain.RoleId;

/**
 * <p>
 * 角色 防腐层
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
public interface RoleGateway extends IBaseGateway<RoleId,Role> {
}

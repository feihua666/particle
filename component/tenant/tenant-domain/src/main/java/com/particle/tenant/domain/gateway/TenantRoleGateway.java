package com.particle.tenant.domain.gateway;

import com.particle.common.domain.gateway.IGateway;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 角色
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 13:58:17
 */
public interface TenantRoleGateway extends IGateway {

	/**
	 * 添加角色
	 * @param code
	 * @param name
	 * @param isSuperAdmin
	 * @param tenantId
	 * @return
	 */
	public Long createRole(String code, String name,boolean isSuperAdmin,Long tenantId);

	/**
	 * 绑定角色与用户关系
	 * @param roleId
	 * @param userId
	 * @param tenantId
	 * @return
	 */
	public void createRoleUserRel(Long roleId, Long userId,Long tenantId);
}

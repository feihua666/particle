package com.particle.tenant.domain.gateway;

import com.particle.common.domain.gateway.IGateway;

import java.util.List;

/**
 * <p>
 * 角色模板依赖防腐网关
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

	/**
	 * 删除功能id范围外的角色功能关系数据
	 * 该功能接口主要用于在租户应用分配功能后，可能功能会减少，将减少的功能联动角色一并减少
	 * @param scopedFuncIds
	 * @return
	 */
	public void deleteOutOfScopeRoleFuncRelByScopedFuncIds(List<Long> scopedFuncIds,Long tenantId);
}

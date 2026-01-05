package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.global.dto.response.SingleResponse;
import com.particle.role.adapter.feign.client.rolefuncrel.rpc.RoleFuncRelRpcFeignClient;
import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.client.dto.command.RoleCreateWithTenantIdCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.client.rolefuncrel.dto.command.RoleFuncRelDeleteWithTenantIdCommand;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelWithTenantIdCreateCommand;
import com.particle.tenant.domain.gateway.TenantRoleGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * 角色依赖
 * </p>
 *
 * @author yangwei
 * @since 2023-04-20 15:56
 */
@Component
public class TenantRoleGatewayImpl implements TenantRoleGateway {
	private RoleRpcFeignClient roleRpcFeignClient;

	private RoleUserRelRpcFeignClient roleUserRelRpcFeignClient;
	private RoleFuncRelRpcFeignClient roleFuncRelRpcFeignClient;

	@Override
	public Long createRole(String code, String name, boolean isSuperAdmin, Long tenantId) {
		RoleCreateWithTenantIdCommand roleCreateCommand = new RoleCreateWithTenantIdCommand();
		roleCreateCommand.setCode(code);
		roleCreateCommand.setName(name);
		roleCreateCommand.setIsSuperadmin(isSuperAdmin);
		roleCreateCommand.setIsDisabled(false);
		roleCreateCommand.setTenantId(tenantId);

        SingleResponse<RoleVO> roleVOSingleResponse = roleRpcFeignClient.createWithTenantId(roleCreateCommand);
        RoleVO roleVO = roleVOSingleResponse.getData();
		return Optional.ofNullable(roleVO).map(RoleVO::getId).orElse(null);
	}

	@Override
	public void createRoleUserRel(Long roleId, Long userId, Long tenantId) {
		RoleUserRelWithTenantIdCreateCommand roleUserRelWithTenantIdCreateCommand = new RoleUserRelWithTenantIdCreateCommand();
		roleUserRelWithTenantIdCreateCommand.setRoleId(roleId);
		roleUserRelWithTenantIdCreateCommand.setUserId(userId);
		roleUserRelWithTenantIdCreateCommand.setTenantId(tenantId);
		roleUserRelRpcFeignClient.createWithTenantId(roleUserRelWithTenantIdCreateCommand);
	}

	@Override
	public void deleteOutOfScopeRoleFuncRelByScopedFuncIds(List<Long> scopedFuncIds,Long tenantId) {
        RoleFuncRelDeleteWithTenantIdCommand roleFuncRelDeleteWithTenantIdCommand = new RoleFuncRelDeleteWithTenantIdCommand();
        roleFuncRelDeleteWithTenantIdCommand.setExcludeFuncIds(scopedFuncIds);
        roleFuncRelDeleteWithTenantIdCommand.setTenantId(tenantId);
        roleFuncRelRpcFeignClient.deleteWithTenantId(roleFuncRelDeleteWithTenantIdCommand);
	}

	@Autowired
	public void setRoleRpcFeignClient(RoleRpcFeignClient roleRpcFeignClient) {
		this.roleRpcFeignClient = roleRpcFeignClient;
	}
	@Autowired
	public void setRoleUserRelRpcFeignClient(RoleUserRelRpcFeignClient roleUserRelRpcFeignClient) {
		this.roleUserRelRpcFeignClient = roleUserRelRpcFeignClient;
	}
	@Autowired
	public void setRoleFuncRelRpcFeignClient(RoleFuncRelRpcFeignClient roleFuncRelRpcFeignClient) {
		this.roleFuncRelRpcFeignClient = roleFuncRelRpcFeignClient;
	}
}

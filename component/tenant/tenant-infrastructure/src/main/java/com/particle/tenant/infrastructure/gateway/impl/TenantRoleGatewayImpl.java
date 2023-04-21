package com.particle.tenant.infrastructure.gateway.impl;

import com.particle.role.adapter.feign.client.roleuserrel.rpc.RoleUserRelRpcFeignClient;
import com.particle.role.adapter.feign.client.rpc.RoleRpcFeignClient;
import com.particle.role.client.dto.command.RoleCreateWithTenantIdCommand;
import com.particle.role.client.dto.data.RoleVO;
import com.particle.role.client.roleuserrel.dto.command.RoleUserRelWithTenantIdCreateCommand;
import com.particle.role.client.roleuserrel.dto.data.RoleUserRelVO;
import com.particle.tenant.domain.gateway.TenantRoleGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

	@Override
	public Long createRole(String code, String name, boolean isSuperAdmin, Long tenantId) {
		RoleCreateWithTenantIdCommand roleCreateCommand = new RoleCreateWithTenantIdCommand();
		roleCreateCommand.setCode(code);
		roleCreateCommand.setName(name);
		roleCreateCommand.setIsSuperadmin(isSuperAdmin);
		roleCreateCommand.setIsDisabled(false);
		roleCreateCommand.setTenantId(tenantId);

		RoleVO add = roleRpcFeignClient.add(roleCreateCommand);
		return Optional.ofNullable(add).map(RoleVO::getId).orElse(null);
	}

	@Override
	public void createRoleUserRel(Long roleId, Long userId, Long tenantId) {
		RoleUserRelWithTenantIdCreateCommand roleUserRelWithTenantIdCreateCommand = new RoleUserRelWithTenantIdCreateCommand();
		roleUserRelWithTenantIdCreateCommand.setRoleId(roleId);
		roleUserRelWithTenantIdCreateCommand.setUserId(userId);
		roleUserRelWithTenantIdCreateCommand.setTenantId(tenantId);
		RoleUserRelVO add = roleUserRelRpcFeignClient.add(roleUserRelWithTenantIdCreateCommand);
	}

	@Autowired
	public void setRoleRpcFeignClient(RoleRpcFeignClient roleRpcFeignClient) {
		this.roleRpcFeignClient = roleRpcFeignClient;
	}
	@Autowired
	public void setRoleUserRelRpcFeignClient(RoleUserRelRpcFeignClient roleUserRelRpcFeignClient) {
		this.roleUserRelRpcFeignClient = roleUserRelRpcFeignClient;
	}
}

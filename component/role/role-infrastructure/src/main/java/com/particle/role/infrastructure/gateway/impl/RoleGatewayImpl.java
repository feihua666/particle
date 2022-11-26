package com.particle.role.infrastructure.gateway.impl;

import com.particle.role.domain.Role;
import com.particle.role.domain.RoleId;
import com.particle.role.domain.gateway.RoleGateway;
import com.particle.role.infrastructure.service.IRoleService;
import com.particle.role.infrastructure.dos.RoleDO;
import com.particle.role.infrastructure.structmapping.RoleInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class RoleGatewayImpl extends AbstractBaseGatewayImpl<RoleId,Role> implements RoleGateway {

	private IRoleService iRoleService;

	@Override
	public Role getById(RoleId roleId) {
		RoleDO byId = iRoleService.getById(roleId.getId());
		Role role = DomainFactory.create(Role.class);
		role = RoleInfrastructureStructMapping.instance. roleDOToRole(role,byId);
		return role;
	}

	@Override
	public boolean doSave(Role role) {
		RoleDO roleDO = RoleInfrastructureStructMapping.instance.roleToRoleDO(role);
		if (roleDO.getId() == null) {
			RoleDO add = iRoleService.add(roleDO);
			role.setId(RoleId.of(add.getId()));
			return add != null;
		}
		RoleDO update = iRoleService.update(roleDO);
		return update != null;
	}

	@Override
	public boolean delete(RoleId roleId) {
		return iRoleService.deleteById(roleId.getId());
	}


	@Autowired
	public void setIRoleService(IRoleService iRoleService) {
		this.iRoleService = iRoleService;
	}
}

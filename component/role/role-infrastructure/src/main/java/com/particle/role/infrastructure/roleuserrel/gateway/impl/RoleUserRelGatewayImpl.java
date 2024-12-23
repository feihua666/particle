package com.particle.role.infrastructure.roleuserrel.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.role.domain.roleuserrel.RoleUserRel;
import com.particle.role.domain.roleuserrel.RoleUserRelId;
import com.particle.role.domain.roleuserrel.gateway.RoleUserRelGateway;
import com.particle.role.infrastructure.roleuserrel.dos.RoleUserRelDO;
import com.particle.role.infrastructure.roleuserrel.service.IRoleUserRelService;
import com.particle.role.infrastructure.roleuserrel.structmapping.RoleUserRelInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色用户关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class RoleUserRelGatewayImpl extends AbstractBaseGatewayImpl<RoleUserRelId,RoleUserRel> implements RoleUserRelGateway {

	private IRoleUserRelService iRoleUserRelService;

	@Override
	public RoleUserRel getById(RoleUserRelId roleUserRelId) {
		RoleUserRelDO byId = iRoleUserRelService.getById(roleUserRelId.getId());
		RoleUserRel roleUserRel = DomainFactory.create(RoleUserRel.class);
		roleUserRel = RoleUserRelInfrastructureStructMapping.instance. roleUserRelDOToRoleUserRel(roleUserRel,byId);
		return roleUserRel;
	}

	@Override
	public boolean doSave(RoleUserRel roleUserRel) {
		RoleUserRelDO roleUserRelDO = RoleUserRelInfrastructureStructMapping.instance.roleUserRelToRoleUserRelDO(roleUserRel);
		if (roleUserRelDO.getId() == null) {
			RoleUserRelDO add = iRoleUserRelService.add(roleUserRelDO);
			roleUserRel.setId(RoleUserRelId.of(add.getId()));
			return add != null;
		}
		RoleUserRelDO update = iRoleUserRelService.update(roleUserRelDO);
		return update != null;
	}

	@Override
	public boolean delete(RoleUserRelId roleUserRelId) {
		return iRoleUserRelService.deleteById(roleUserRelId.getId());
	}

	@Override
	public boolean delete(RoleUserRelId id, IdCommand idCommand) {
		return iRoleUserRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIRoleUserRelService(IRoleUserRelService iRoleUserRelService) {
		this.iRoleUserRelService = iRoleUserRelService;
	}
}

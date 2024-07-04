package com.particle.role.infrastructure.roledatascoperel.gateway.impl;

import com.particle.role.domain.roledatascoperel.RoleDataScopeRel;
import com.particle.role.domain.roledatascoperel.RoleDataScopeRelId;
import com.particle.role.domain.roledatascoperel.gateway.RoleDataScopeRelGateway;
import com.particle.role.infrastructure.roledatascoperel.service.IRoleDataScopeRelService;
import com.particle.role.infrastructure.roledatascoperel.dos.RoleDataScopeRelDO;
import com.particle.role.infrastructure.roledatascoperel.structmapping.RoleDataScopeRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色数据范围关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-07-01 16:45:06
 */
@Component
public class RoleDataScopeRelGatewayImpl extends AbstractBaseGatewayImpl<RoleDataScopeRelId,RoleDataScopeRel> implements RoleDataScopeRelGateway {

	private IRoleDataScopeRelService iRoleDataScopeRelService;

	@Override
	public RoleDataScopeRel getById(RoleDataScopeRelId roleDataScopeRelId) {
		RoleDataScopeRelDO byId = iRoleDataScopeRelService.getById(roleDataScopeRelId.getId());
		RoleDataScopeRel roleDataScopeRel = DomainFactory.create(RoleDataScopeRel.class);
		roleDataScopeRel = RoleDataScopeRelInfrastructureStructMapping.instance. roleDataScopeRelDOToRoleDataScopeRel(roleDataScopeRel,byId);
		return roleDataScopeRel;
	}

	@Override
	public boolean doSave(RoleDataScopeRel roleDataScopeRel) {
		RoleDataScopeRelDO roleDataScopeRelDO = RoleDataScopeRelInfrastructureStructMapping.instance.roleDataScopeRelToRoleDataScopeRelDO(roleDataScopeRel);
		if (roleDataScopeRelDO.getId() == null) {
			roleDataScopeRelDO.setAddControl(roleDataScopeRel.getAddControl());
			RoleDataScopeRelDO add = iRoleDataScopeRelService.add(roleDataScopeRelDO);
			roleDataScopeRel.setId(RoleDataScopeRelId.of(add.getId()));
			return add != null;
		}
		roleDataScopeRelDO.setUpdateControl(roleDataScopeRel.getUpdateControl());
		RoleDataScopeRelDO update = iRoleDataScopeRelService.update(roleDataScopeRelDO);
		return update != null;
	}

	@Override
	public boolean delete(RoleDataScopeRelId roleDataScopeRelId) {
		return iRoleDataScopeRelService.deleteById(roleDataScopeRelId.getId());
	}


	@Autowired
	public void setIRoleDataScopeRelService(IRoleDataScopeRelService iRoleDataScopeRelService) {
		this.iRoleDataScopeRelService = iRoleDataScopeRelService;
	}
}

package com.particle.role.infrastructure.rolefuncrel.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.role.domain.rolefuncrel.RoleFuncRel;
import com.particle.role.domain.rolefuncrel.RoleFuncRelId;
import com.particle.role.domain.rolefuncrel.gateway.RoleFuncRelGateway;
import com.particle.role.infrastructure.rolefuncrel.dos.RoleFuncRelDO;
import com.particle.role.infrastructure.rolefuncrel.service.IRoleFuncRelService;
import com.particle.role.infrastructure.rolefuncrel.structmapping.RoleFuncRelInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色菜单功能关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-11-25
 */
@Component
public class RoleFuncRelGatewayImpl extends AbstractBaseGatewayImpl<RoleFuncRelId,RoleFuncRel> implements RoleFuncRelGateway {

	private IRoleFuncRelService iRoleFuncRelService;

	@Override
	public RoleFuncRel getById(RoleFuncRelId roleFuncRelId) {
		RoleFuncRelDO byId = iRoleFuncRelService.getById(roleFuncRelId.getId());
		RoleFuncRel roleFuncRel = DomainFactory.create(RoleFuncRel.class);
		roleFuncRel = RoleFuncRelInfrastructureStructMapping.instance. roleFuncRelDOToRoleFuncRel(roleFuncRel,byId);
		return roleFuncRel;
	}

	@Override
	public boolean doSave(RoleFuncRel roleFuncRel) {
		RoleFuncRelDO roleFuncRelDO = RoleFuncRelInfrastructureStructMapping.instance.roleFuncRelToRoleFuncRelDO(roleFuncRel);
		if (roleFuncRelDO.getId() == null) {
			RoleFuncRelDO add = iRoleFuncRelService.add(roleFuncRelDO);
			roleFuncRel.setId(RoleFuncRelId.of(add.getId()));
			return add != null;
		}
		RoleFuncRelDO update = iRoleFuncRelService.update(roleFuncRelDO);
		return update != null;
	}

	@Override
	public boolean delete(RoleFuncRelId roleFuncRelId) {
		return iRoleFuncRelService.deleteById(roleFuncRelId.getId());
	}

	@Override
	public boolean delete(RoleFuncRelId id, IdCommand idCommand) {
		return iRoleFuncRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIRoleFuncRelService(IRoleFuncRelService iRoleFuncRelService) {
		this.iRoleFuncRelService = iRoleFuncRelService;
	}
}

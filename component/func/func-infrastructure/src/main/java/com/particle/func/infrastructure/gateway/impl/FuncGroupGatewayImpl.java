package com.particle.func.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.func.domain.FuncGroup;
import com.particle.func.domain.FuncGroupId;
import com.particle.func.domain.gateway.FuncGroupGateway;
import com.particle.func.infrastructure.dos.FuncGroupDO;
import com.particle.func.infrastructure.service.IFuncGroupService;
import com.particle.func.infrastructure.structmapping.FuncGroupInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能组 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-12-02
 */
@Component
public class FuncGroupGatewayImpl extends AbstractBaseGatewayImpl<FuncGroupId,FuncGroup> implements FuncGroupGateway {

	private IFuncGroupService iFuncGroupService;

	@Override
	public FuncGroup getById(FuncGroupId funcGroupId) {
		FuncGroupDO byId = iFuncGroupService.getById(funcGroupId.getId());
		FuncGroup funcGroup = DomainFactory.create(FuncGroup.class);
		funcGroup = FuncGroupInfrastructureStructMapping.instance. funcGroupDOToFuncGroup(funcGroup,byId);
		return funcGroup;
	}

	@Override
	public boolean doSave(FuncGroup funcGroup) {
		FuncGroupDO funcGroupDO = FuncGroupInfrastructureStructMapping.instance.funcGroupToFuncGroupDO(funcGroup);
		if (funcGroupDO.getId() == null) {
			FuncGroupDO add = iFuncGroupService.add(funcGroupDO);
			funcGroup.setId(FuncGroupId.of(add.getId()));
			return add != null;
		}
		FuncGroupDO update = iFuncGroupService.update(funcGroupDO);
		return update != null;
	}

	@Override
	public boolean delete(FuncGroupId funcGroupId) {
		return iFuncGroupService.deleteById(funcGroupId.getId());
	}

	@Override
	public boolean delete(FuncGroupId id, IdCommand idCommand) {
		return iFuncGroupService.deleteById(idCommand);
	}

	@Autowired
	public void setIFuncGroupService(IFuncGroupService iFuncGroupService) {
		this.iFuncGroupService = iFuncGroupService;
	}
}

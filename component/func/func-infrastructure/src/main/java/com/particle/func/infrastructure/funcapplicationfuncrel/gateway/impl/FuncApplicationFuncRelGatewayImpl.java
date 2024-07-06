package com.particle.func.infrastructure.funcapplicationfuncrel.gateway.impl;

import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRel;
import com.particle.func.domain.funcapplicationfuncrel.FuncApplicationFuncRelId;
import com.particle.func.domain.funcapplicationfuncrel.gateway.FuncApplicationFuncRelGateway;
import com.particle.func.infrastructure.funcapplicationfuncrel.service.IFuncApplicationFuncRelService;
import com.particle.func.infrastructure.funcapplicationfuncrel.dos.FuncApplicationFuncRelDO;
import com.particle.func.infrastructure.funcapplicationfuncrel.structmapping.FuncApplicationFuncRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能应用功能关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:15:29
 */
@Component
public class FuncApplicationFuncRelGatewayImpl extends AbstractBaseGatewayImpl<FuncApplicationFuncRelId,FuncApplicationFuncRel> implements FuncApplicationFuncRelGateway {

	private IFuncApplicationFuncRelService iFuncApplicationFuncRelService;

	@Override
	public FuncApplicationFuncRel getById(FuncApplicationFuncRelId funcApplicationFuncRelId) {
		FuncApplicationFuncRelDO byId = iFuncApplicationFuncRelService.getById(funcApplicationFuncRelId.getId());
		FuncApplicationFuncRel funcApplicationFuncRel = DomainFactory.create(FuncApplicationFuncRel.class);
		funcApplicationFuncRel = FuncApplicationFuncRelInfrastructureStructMapping.instance. funcApplicationFuncRelDOToFuncApplicationFuncRel(funcApplicationFuncRel,byId);
		return funcApplicationFuncRel;
	}

	@Override
	public boolean doSave(FuncApplicationFuncRel funcApplicationFuncRel) {
		FuncApplicationFuncRelDO funcApplicationFuncRelDO = FuncApplicationFuncRelInfrastructureStructMapping.instance.funcApplicationFuncRelToFuncApplicationFuncRelDO(funcApplicationFuncRel);
		if (funcApplicationFuncRelDO.getId() == null) {
			funcApplicationFuncRelDO.setAddControl(funcApplicationFuncRel.getAddControl());
			FuncApplicationFuncRelDO add = iFuncApplicationFuncRelService.add(funcApplicationFuncRelDO);
			funcApplicationFuncRel.setId(FuncApplicationFuncRelId.of(add.getId()));
			return add != null;
		}
		funcApplicationFuncRelDO.setUpdateControl(funcApplicationFuncRel.getUpdateControl());
		FuncApplicationFuncRelDO update = iFuncApplicationFuncRelService.update(funcApplicationFuncRelDO);
		return update != null;
	}

	@Override
	public boolean delete(FuncApplicationFuncRelId funcApplicationFuncRelId) {
		return iFuncApplicationFuncRelService.deleteById(funcApplicationFuncRelId.getId());
	}

	@Override
	public boolean delete(FuncApplicationFuncRelId id, IdCommand idCommand) {
		return iFuncApplicationFuncRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIFuncApplicationFuncRelService(IFuncApplicationFuncRelService iFuncApplicationFuncRelService) {
		this.iFuncApplicationFuncRelService = iFuncApplicationFuncRelService;
	}
}

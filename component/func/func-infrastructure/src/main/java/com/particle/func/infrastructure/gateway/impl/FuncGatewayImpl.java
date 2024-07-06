package com.particle.func.infrastructure.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.func.domain.Func;
import com.particle.func.domain.FuncId;
import com.particle.func.domain.gateway.FuncGateway;
import com.particle.func.infrastructure.dos.FuncDO;
import com.particle.func.infrastructure.service.IFuncService;
import com.particle.func.infrastructure.structmapping.FuncInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 菜单功能 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2022-07-19
 */
@Component
public class FuncGatewayImpl extends AbstractBaseGatewayImpl<FuncId,Func> implements FuncGateway {

	private IFuncService iFuncService;

	@Override
	public Func getById(FuncId funcId) {
		FuncDO byId = iFuncService.getById(funcId.getId());
		Func func = DomainFactory.create(Func.class);
		func = FuncInfrastructureStructMapping.instance. funcDOToFunc(func,byId);
		return func;
	}

	@Override
	public boolean doSave(Func func) {
		FuncDO funcDO = FuncInfrastructureStructMapping.instance.funcToFuncDO(func);
		if (funcDO.getId() == null) {
			FuncDO add = iFuncService.add(funcDO);
			func.setId(FuncId.of(add.getId()));
			return add != null;
		}
		FuncDO update = iFuncService.update(funcDO);
		return update != null;
	}

	@Override
	public boolean delete(FuncId funcId) {
		return iFuncService.deleteById(funcId.getId());
	}

	@Override
	public boolean delete(FuncId id, IdCommand idCommand) {
		return iFuncService.deleteById(idCommand);
	}

	@Autowired
	public void setIFuncService(IFuncService iFuncService) {
		this.iFuncService = iFuncService;
	}
}

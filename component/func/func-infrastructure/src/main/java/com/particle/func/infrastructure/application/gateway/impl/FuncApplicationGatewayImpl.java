package com.particle.func.infrastructure.application.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.func.domain.application.FuncApplication;
import com.particle.func.domain.application.FuncApplicationId;
import com.particle.func.domain.application.gateway.FuncApplicationGateway;
import com.particle.func.infrastructure.application.dos.FuncApplicationDO;
import com.particle.func.infrastructure.application.service.IFuncApplicationService;
import com.particle.func.infrastructure.application.structmapping.FuncApplicationInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 功能应用 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-04-17 10:12:23
 */
@Component
public class FuncApplicationGatewayImpl extends AbstractBaseGatewayImpl<FuncApplicationId,FuncApplication> implements FuncApplicationGateway {

	private IFuncApplicationService iFuncApplicationService;

	@Override
	public FuncApplication getById(FuncApplicationId funcApplicationId) {
		FuncApplicationDO byId = iFuncApplicationService.getById(funcApplicationId.getId());
		FuncApplication funcApplication = DomainFactory.create(FuncApplication.class);
		funcApplication = FuncApplicationInfrastructureStructMapping.instance. funcApplicationDOToFuncApplication(funcApplication,byId);
		return funcApplication;
	}

	@Override
	public boolean doSave(FuncApplication funcApplication) {
		FuncApplicationDO funcApplicationDO = FuncApplicationInfrastructureStructMapping.instance.funcApplicationToFuncApplicationDO(funcApplication);
		if (funcApplicationDO.getId() == null) {
			funcApplicationDO.setAddControl(funcApplication.getAddControl());
			FuncApplicationDO add = iFuncApplicationService.add(funcApplicationDO);
			funcApplication.setId(FuncApplicationId.of(add.getId()));
			return add != null;
		}
		funcApplicationDO.setUpdateControl(funcApplication.getUpdateControl());
		FuncApplicationDO update = iFuncApplicationService.update(funcApplicationDO);
		return update != null;
	}

	@Override
	public boolean delete(FuncApplicationId funcApplicationId) {
		return iFuncApplicationService.deleteById(funcApplicationId.getId());
	}

	@Override
	public boolean delete(FuncApplicationId id, IdCommand idCommand) {
		return iFuncApplicationService.deleteById(idCommand);
	}

	@Autowired
	public void setIFuncApplicationService(IFuncApplicationService iFuncApplicationService) {
		this.iFuncApplicationService = iFuncApplicationService;
	}
}

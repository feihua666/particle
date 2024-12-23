package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.lowcode.domain.generator.LowcodeModel;
import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelGateway;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelService;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeModelInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码模型 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
public class LowcodeModelGatewayImpl extends AbstractBaseGatewayImpl<LowcodeModelId,LowcodeModel> implements LowcodeModelGateway {

	private ILowcodeModelService iLowcodeModelService;

	@Override
	public LowcodeModel getById(LowcodeModelId lowcodeModelId) {
		LowcodeModelDO byId = iLowcodeModelService.getById(lowcodeModelId.getId());
		LowcodeModel lowcodeModel = DomainFactory.create(LowcodeModel.class);
		lowcodeModel = LowcodeModelInfrastructureStructMapping.instance. lowcodeModelDOToLowcodeModel(lowcodeModel,byId);
		return lowcodeModel;
	}

	@Override
	public boolean doSave(LowcodeModel lowcodeModel) {
		LowcodeModelDO lowcodeModelDO = LowcodeModelInfrastructureStructMapping.instance.lowcodeModelToLowcodeModelDO(lowcodeModel);
		if (lowcodeModelDO.getId() == null) {
			lowcodeModelDO.setAddControl(lowcodeModel.getAddControl());
			LowcodeModelDO add = iLowcodeModelService.add(lowcodeModelDO);
			lowcodeModel.setId(LowcodeModelId.of(add.getId()));
			return add != null;
		}
		lowcodeModelDO.setUpdateControl(lowcodeModel.getUpdateControl());
		LowcodeModelDO update = iLowcodeModelService.update(lowcodeModelDO);
		return update != null;
	}

	@Override
	public boolean delete(LowcodeModelId lowcodeModelId) {
		return iLowcodeModelService.deleteById(lowcodeModelId.getId());
	}

	@Override
	public boolean delete(LowcodeModelId id, IdCommand idCommand) {
		return iLowcodeModelService.deleteById(idCommand);
	}

	@Autowired
	public void setILowcodeModelService(ILowcodeModelService iLowcodeModelService) {
		this.iLowcodeModelService = iLowcodeModelService;
	}
}

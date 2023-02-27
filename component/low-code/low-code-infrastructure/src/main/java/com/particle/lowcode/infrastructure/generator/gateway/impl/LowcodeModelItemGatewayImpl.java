package com.particle.lowcode.infrastructure.generator.gateway.impl;

import com.particle.lowcode.domain.generator.LowcodeModelId;
import com.particle.lowcode.domain.generator.LowcodeModelItem;
import com.particle.lowcode.domain.generator.LowcodeModelItemId;
import com.particle.lowcode.domain.generator.gateway.LowcodeModelItemGateway;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeModelItemService;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeModelItemDO;
import com.particle.lowcode.infrastructure.generator.structmapping.LowcodeModelItemInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 低代码模型项目 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-01-05
 */
@Component
public class LowcodeModelItemGatewayImpl extends AbstractBaseGatewayImpl<LowcodeModelItemId,LowcodeModelItem> implements LowcodeModelItemGateway {

	private ILowcodeModelItemService iLowcodeModelItemService;

	@Override
	public LowcodeModelItem getById(LowcodeModelItemId lowcodeModelItemId) {
		LowcodeModelItemDO byId = iLowcodeModelItemService.getById(lowcodeModelItemId.getId());
		LowcodeModelItem lowcodeModelItem = DomainFactory.create(LowcodeModelItem.class);
		lowcodeModelItem = LowcodeModelItemInfrastructureStructMapping.instance. lowcodeModelItemDOToLowcodeModelItem(lowcodeModelItem,byId);
		return lowcodeModelItem;
	}

	@Override
	public boolean doSave(LowcodeModelItem lowcodeModelItem) {
		LowcodeModelItemDO lowcodeModelItemDO = LowcodeModelItemInfrastructureStructMapping.instance.lowcodeModelItemToLowcodeModelItemDO(lowcodeModelItem);
		if (lowcodeModelItemDO.getId() == null) {
			lowcodeModelItemDO.setAddControl(lowcodeModelItem.getAddControl());
			LowcodeModelItemDO add = iLowcodeModelItemService.add(lowcodeModelItemDO);
			lowcodeModelItem.setId(LowcodeModelItemId.of(add.getId()));
			return add != null;
		}
		lowcodeModelItemDO.setUpdateControl(lowcodeModelItem.getUpdateControl());
		LowcodeModelItemDO update = iLowcodeModelItemService.update(lowcodeModelItemDO);
		return update != null;
	}

	@Override
	public boolean delete(LowcodeModelItemId lowcodeModelItemId) {
		return iLowcodeModelItemService.deleteById(lowcodeModelItemId.getId());
	}


	@Autowired
	public void setILowcodeModelItemService(ILowcodeModelItemService iLowcodeModelItemService) {
		this.iLowcodeModelItemService = iLowcodeModelItemService;
	}

	@Override
	public boolean clearByLowcodeModelId(LowcodeModelId lowcodeModelId) {
		return iLowcodeModelItemService.deleteByColumn(lowcodeModelId.getId(), LowcodeModelItemDO::getLowcodeModelId);
	}
}

package com.particle.openplatform.infrastructure.providerrecord.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecord;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordId;
import com.particle.openplatform.domain.providerrecord.gateway.OpenplatformProviderRecordGateway;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordService;
import com.particle.openplatform.infrastructure.providerrecord.structmapping.OpenplatformProviderRecordInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口供应商调用记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Component
public class OpenplatformProviderRecordGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderRecordId,OpenplatformProviderRecord> implements OpenplatformProviderRecordGateway {

	private IOpenplatformProviderRecordService iOpenplatformProviderRecordService;

	@Override
	public OpenplatformProviderRecord getById(OpenplatformProviderRecordId openplatformProviderRecordId) {
		OpenplatformProviderRecordDO byId = iOpenplatformProviderRecordService.getById(openplatformProviderRecordId.getId());
		OpenplatformProviderRecord openplatformProviderRecord = DomainFactory.create(OpenplatformProviderRecord.class);
		openplatformProviderRecord = OpenplatformProviderRecordInfrastructureStructMapping.instance. openplatformProviderRecordDOToOpenplatformProviderRecord(openplatformProviderRecord,byId);
		return openplatformProviderRecord;
	}

	@Override
	public boolean doSave(OpenplatformProviderRecord openplatformProviderRecord) {
		OpenplatformProviderRecordDO openplatformProviderRecordDO = OpenplatformProviderRecordInfrastructureStructMapping.instance.openplatformProviderRecordToOpenplatformProviderRecordDO(openplatformProviderRecord);
		if (openplatformProviderRecordDO.getId() == null) {
			openplatformProviderRecordDO.setAddControl(openplatformProviderRecord.getAddControl());
			OpenplatformProviderRecordDO add = iOpenplatformProviderRecordService.add(openplatformProviderRecordDO);
			openplatformProviderRecord.setId(OpenplatformProviderRecordId.of(add.getId()));
			return add != null;
		}
		openplatformProviderRecordDO.setUpdateControl(openplatformProviderRecord.getUpdateControl());
		OpenplatformProviderRecordDO update = iOpenplatformProviderRecordService.update(openplatformProviderRecordDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformProviderRecordId openplatformProviderRecordId) {
		return iOpenplatformProviderRecordService.deleteById(openplatformProviderRecordId.getId());
	}

	@Override
	public boolean delete(OpenplatformProviderRecordId id, IdCommand idCommand) {
		return iOpenplatformProviderRecordService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformProviderRecordService(IOpenplatformProviderRecordService iOpenplatformProviderRecordService) {
		this.iOpenplatformProviderRecordService = iOpenplatformProviderRecordService;
	}
}

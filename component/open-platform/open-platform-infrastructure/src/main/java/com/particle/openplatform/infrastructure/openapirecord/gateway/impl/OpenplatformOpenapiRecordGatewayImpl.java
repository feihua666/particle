package com.particle.openplatform.infrastructure.openapirecord.gateway.impl;

import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecord;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordId;
import com.particle.openplatform.domain.openapirecord.gateway.OpenplatformOpenapiRecordGateway;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordService;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.infrastructure.openapirecord.structmapping.OpenplatformOpenapiRecordInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口调用记录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Component
public class OpenplatformOpenapiRecordGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordId,OpenplatformOpenapiRecord> implements OpenplatformOpenapiRecordGateway {

	private IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService;

	@Override
	public OpenplatformOpenapiRecord getById(OpenplatformOpenapiRecordId openplatformOpenapiRecordId) {
		OpenplatformOpenapiRecordDO byId = iOpenplatformOpenapiRecordService.getById(openplatformOpenapiRecordId.getId());
		OpenplatformOpenapiRecord openplatformOpenapiRecord = DomainFactory.create(OpenplatformOpenapiRecord.class);
		openplatformOpenapiRecord = OpenplatformOpenapiRecordInfrastructureStructMapping.instance. openplatformOpenapiRecordDOToOpenplatformOpenapiRecord(openplatformOpenapiRecord,byId);
		return openplatformOpenapiRecord;
	}

	@Override
	public boolean doSave(OpenplatformOpenapiRecord openplatformOpenapiRecord) {
		OpenplatformOpenapiRecordDO openplatformOpenapiRecordDO = OpenplatformOpenapiRecordInfrastructureStructMapping.instance.openplatformOpenapiRecordToOpenplatformOpenapiRecordDO(openplatformOpenapiRecord);
		if (openplatformOpenapiRecordDO.getId() == null) {
			openplatformOpenapiRecordDO.setAddControl(openplatformOpenapiRecord.getAddControl());
			OpenplatformOpenapiRecordDO add = iOpenplatformOpenapiRecordService.add(openplatformOpenapiRecordDO);
			openplatformOpenapiRecord.setId(OpenplatformOpenapiRecordId.of(add.getId()));
			return add != null;
		}
		openplatformOpenapiRecordDO.setUpdateControl(openplatformOpenapiRecord.getUpdateControl());
		OpenplatformOpenapiRecordDO update = iOpenplatformOpenapiRecordService.update(openplatformOpenapiRecordDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformOpenapiRecordId openplatformOpenapiRecordId) {
		return iOpenplatformOpenapiRecordService.deleteById(openplatformOpenapiRecordId.getId());
	}


	@Autowired
	public void setIOpenplatformOpenapiRecordService(IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService) {
		this.iOpenplatformOpenapiRecordService = iOpenplatformOpenapiRecordService;
	}
}

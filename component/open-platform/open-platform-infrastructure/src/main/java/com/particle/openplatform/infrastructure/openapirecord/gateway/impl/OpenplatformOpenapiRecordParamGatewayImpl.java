package com.particle.openplatform.infrastructure.openapirecord.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParam;
import com.particle.openplatform.domain.openapirecord.OpenplatformOpenapiRecordParamId;
import com.particle.openplatform.domain.openapirecord.gateway.OpenplatformOpenapiRecordParamGateway;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordParamService;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordParamDO;
import com.particle.openplatform.infrastructure.openapirecord.structmapping.OpenplatformOpenapiRecordParamInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口调用记录参数 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Component
public class OpenplatformOpenapiRecordParamGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiRecordParamId,OpenplatformOpenapiRecordParam> implements OpenplatformOpenapiRecordParamGateway {

	private IOpenplatformOpenapiRecordParamService iOpenplatformOpenapiRecordParamService;

	@Override
	public OpenplatformOpenapiRecordParam getById(OpenplatformOpenapiRecordParamId openplatformOpenapiRecordParamId) {
		OpenplatformOpenapiRecordParamDO byId = iOpenplatformOpenapiRecordParamService.getById(openplatformOpenapiRecordParamId.getId());
		OpenplatformOpenapiRecordParam openplatformOpenapiRecordParam = DomainFactory.create(OpenplatformOpenapiRecordParam.class);
		openplatformOpenapiRecordParam = OpenplatformOpenapiRecordParamInfrastructureStructMapping.instance. openplatformOpenapiRecordParamDOToOpenplatformOpenapiRecordParam(openplatformOpenapiRecordParam,byId);
		return openplatformOpenapiRecordParam;
	}

	@Override
	public boolean doSave(OpenplatformOpenapiRecordParam openplatformOpenapiRecordParam) {
		OpenplatformOpenapiRecordParamDO openplatformOpenapiRecordParamDO = OpenplatformOpenapiRecordParamInfrastructureStructMapping.instance.openplatformOpenapiRecordParamToOpenplatformOpenapiRecordParamDO(openplatformOpenapiRecordParam);
		if (openplatformOpenapiRecordParamDO.getId() == null) {
			openplatformOpenapiRecordParamDO.setAddControl(openplatformOpenapiRecordParam.getAddControl());
			OpenplatformOpenapiRecordParamDO add = iOpenplatformOpenapiRecordParamService.add(openplatformOpenapiRecordParamDO);
			openplatformOpenapiRecordParam.setId(OpenplatformOpenapiRecordParamId.of(add.getId()));
			return add != null;
		}
		openplatformOpenapiRecordParamDO.setUpdateControl(openplatformOpenapiRecordParam.getUpdateControl());
		OpenplatformOpenapiRecordParamDO update = iOpenplatformOpenapiRecordParamService.update(openplatformOpenapiRecordParamDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformOpenapiRecordParamId openplatformOpenapiRecordParamId) {
		return iOpenplatformOpenapiRecordParamService.deleteById(openplatformOpenapiRecordParamId.getId());
	}

	@Override
	public boolean delete(OpenplatformOpenapiRecordParamId id, IdCommand idCommand) {
		return iOpenplatformOpenapiRecordParamService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformOpenapiRecordParamService(IOpenplatformOpenapiRecordParamService iOpenplatformOpenapiRecordParamService) {
		this.iOpenplatformOpenapiRecordParamService = iOpenplatformOpenapiRecordParamService;
	}
}

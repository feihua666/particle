package com.particle.openplatform.infrastructure.providerrecord.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParam;
import com.particle.openplatform.domain.providerrecord.OpenplatformProviderRecordParamId;
import com.particle.openplatform.domain.providerrecord.gateway.OpenplatformProviderRecordParamGateway;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordParamService;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordParamDO;
import com.particle.openplatform.infrastructure.providerrecord.structmapping.OpenplatformProviderRecordParamInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Component
public class OpenplatformProviderRecordParamGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderRecordParamId,OpenplatformProviderRecordParam> implements OpenplatformProviderRecordParamGateway {

	private IOpenplatformProviderRecordParamService iOpenplatformProviderRecordParamService;

	@Override
	public OpenplatformProviderRecordParam getById(OpenplatformProviderRecordParamId openplatformProviderRecordParamId) {
		OpenplatformProviderRecordParamDO byId = iOpenplatformProviderRecordParamService.getById(openplatformProviderRecordParamId.getId());
		OpenplatformProviderRecordParam openplatformProviderRecordParam = DomainFactory.create(OpenplatformProviderRecordParam.class);
		openplatformProviderRecordParam = OpenplatformProviderRecordParamInfrastructureStructMapping.instance. openplatformProviderRecordParamDOToOpenplatformProviderRecordParam(openplatformProviderRecordParam,byId);
		return openplatformProviderRecordParam;
	}

	@Override
	public boolean doSave(OpenplatformProviderRecordParam openplatformProviderRecordParam) {
		OpenplatformProviderRecordParamDO openplatformProviderRecordParamDO = OpenplatformProviderRecordParamInfrastructureStructMapping.instance.openplatformProviderRecordParamToOpenplatformProviderRecordParamDO(openplatformProviderRecordParam);
		if (openplatformProviderRecordParamDO.getId() == null) {
			openplatformProviderRecordParamDO.setAddControl(openplatformProviderRecordParam.getAddControl());
			OpenplatformProviderRecordParamDO add = iOpenplatformProviderRecordParamService.add(openplatformProviderRecordParamDO);
			openplatformProviderRecordParam.setId(OpenplatformProviderRecordParamId.of(add.getId()));
			return add != null;
		}
		openplatformProviderRecordParamDO.setUpdateControl(openplatformProviderRecordParam.getUpdateControl());
		OpenplatformProviderRecordParamDO update = iOpenplatformProviderRecordParamService.update(openplatformProviderRecordParamDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformProviderRecordParamId openplatformProviderRecordParamId) {
		return iOpenplatformProviderRecordParamService.deleteById(openplatformProviderRecordParamId.getId());
	}

	@Override
	public boolean delete(OpenplatformProviderRecordParamId id, IdCommand idCommand) {
		return iOpenplatformProviderRecordParamService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformProviderRecordParamService(IOpenplatformProviderRecordParamService iOpenplatformProviderRecordParamService) {
		this.iOpenplatformProviderRecordParamService = iOpenplatformProviderRecordParamService;
	}
}

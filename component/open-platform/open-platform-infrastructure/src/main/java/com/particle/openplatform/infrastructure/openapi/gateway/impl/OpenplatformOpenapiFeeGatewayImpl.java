package com.particle.openplatform.infrastructure.openapi.gateway.impl;

import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFee;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiFeeId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiFeeGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiFeeService;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiFeeDO;
import com.particle.openplatform.infrastructure.openapi.structmapping.OpenplatformOpenapiFeeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台开放接口费用 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Component
public class OpenplatformOpenapiFeeGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformOpenapiFeeId,OpenplatformOpenapiFee> implements OpenplatformOpenapiFeeGateway {

	private IOpenplatformOpenapiFeeService iOpenplatformOpenapiFeeService;

	@Override
	public OpenplatformOpenapiFee getById(OpenplatformOpenapiFeeId openplatformOpenapiFeeId) {
		OpenplatformOpenapiFeeDO byId = iOpenplatformOpenapiFeeService.getById(openplatformOpenapiFeeId.getId());
		OpenplatformOpenapiFee openplatformOpenapiFee = DomainFactory.create(OpenplatformOpenapiFee.class);
		openplatformOpenapiFee = OpenplatformOpenapiFeeInfrastructureStructMapping.instance. openplatformOpenapiFeeDOToOpenplatformOpenapiFee(openplatformOpenapiFee,byId);
		return openplatformOpenapiFee;
	}

	@Override
	public boolean doSave(OpenplatformOpenapiFee openplatformOpenapiFee) {
		OpenplatformOpenapiFeeDO openplatformOpenapiFeeDO = OpenplatformOpenapiFeeInfrastructureStructMapping.instance.openplatformOpenapiFeeToOpenplatformOpenapiFeeDO(openplatformOpenapiFee);
		if (openplatformOpenapiFeeDO.getId() == null) {
			openplatformOpenapiFeeDO.setAddControl(openplatformOpenapiFee.getAddControl());
			OpenplatformOpenapiFeeDO add = iOpenplatformOpenapiFeeService.add(openplatformOpenapiFeeDO);
			openplatformOpenapiFee.setId(OpenplatformOpenapiFeeId.of(add.getId()));
			return add != null;
		}
		openplatformOpenapiFeeDO.setUpdateControl(openplatformOpenapiFee.getUpdateControl());
		OpenplatformOpenapiFeeDO update = iOpenplatformOpenapiFeeService.update(openplatformOpenapiFeeDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformOpenapiFeeId openplatformOpenapiFeeId) {
		return iOpenplatformOpenapiFeeService.deleteById(openplatformOpenapiFeeId.getId());
	}


	@Autowired
	public void setIOpenplatformOpenapiFeeService(IOpenplatformOpenapiFeeService iOpenplatformOpenapiFeeService) {
		this.iOpenplatformOpenapiFeeService = iOpenplatformOpenapiFeeService;
	}
}

package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocParamFieldId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocParamFieldGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocParamFieldService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocParamFieldInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档参数字段 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Component
public class OpenplatformDocApiDocParamFieldGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocParamFieldId,OpenplatformDocApiDocParamField> implements OpenplatformDocApiDocParamFieldGateway {

	private IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService;

	@Override
	public OpenplatformDocApiDocParamField getById(OpenplatformDocApiDocParamFieldId openplatformDocApiDocParamFieldId) {
		OpenplatformDocApiDocParamFieldDO byId = iOpenplatformDocApiDocParamFieldService.getById(openplatformDocApiDocParamFieldId.getId());
		OpenplatformDocApiDocParamField openplatformDocApiDocParamField = DomainFactory.create(OpenplatformDocApiDocParamField.class);
		openplatformDocApiDocParamField = OpenplatformDocApiDocParamFieldInfrastructureStructMapping.instance. openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamField(openplatformDocApiDocParamField,byId);
		return openplatformDocApiDocParamField;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocParamField openplatformDocApiDocParamField) {
		OpenplatformDocApiDocParamFieldDO openplatformDocApiDocParamFieldDO = OpenplatformDocApiDocParamFieldInfrastructureStructMapping.instance.openplatformDocApiDocParamFieldToOpenplatformDocApiDocParamFieldDO(openplatformDocApiDocParamField);
		if (openplatformDocApiDocParamFieldDO.getId() == null) {
			openplatformDocApiDocParamFieldDO.setAddControl(openplatformDocApiDocParamField.getAddControl());
			OpenplatformDocApiDocParamFieldDO add = iOpenplatformDocApiDocParamFieldService.add(openplatformDocApiDocParamFieldDO);
			openplatformDocApiDocParamField.setId(OpenplatformDocApiDocParamFieldId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocParamFieldDO.setUpdateControl(openplatformDocApiDocParamField.getUpdateControl());
		OpenplatformDocApiDocParamFieldDO update = iOpenplatformDocApiDocParamFieldService.update(openplatformDocApiDocParamFieldDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocParamFieldId openplatformDocApiDocParamFieldId) {
		return iOpenplatformDocApiDocParamFieldService.deleteById(openplatformDocApiDocParamFieldId.getId());
	}


	@Autowired
	public void setIOpenplatformDocApiDocParamFieldService(IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService) {
		this.iOpenplatformDocApiDocParamFieldService = iOpenplatformDocApiDocParamFieldService;
	}
}

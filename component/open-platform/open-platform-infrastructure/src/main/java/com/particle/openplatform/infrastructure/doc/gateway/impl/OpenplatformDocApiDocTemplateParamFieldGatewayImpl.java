package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamField;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateParamFieldId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateParamFieldGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateParamFieldService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档模板参数字段 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Component
public class OpenplatformDocApiDocTemplateParamFieldGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocTemplateParamFieldId,OpenplatformDocApiDocTemplateParamField> implements OpenplatformDocApiDocTemplateParamFieldGateway {

	private IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService;

	@Override
	public OpenplatformDocApiDocTemplateParamField getById(OpenplatformDocApiDocTemplateParamFieldId openplatformDocApiDocTemplateParamFieldId) {
		OpenplatformDocApiDocTemplateParamFieldDO byId = iOpenplatformDocApiDocTemplateParamFieldService.getById(openplatformDocApiDocTemplateParamFieldId.getId());
		OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField = DomainFactory.create(OpenplatformDocApiDocTemplateParamField.class);
		openplatformDocApiDocTemplateParamField = OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping.instance. openplatformDocApiDocTemplateParamFieldDOToOpenplatformDocApiDocTemplateParamField(openplatformDocApiDocTemplateParamField,byId);
		return openplatformDocApiDocTemplateParamField;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocTemplateParamField openplatformDocApiDocTemplateParamField) {
		OpenplatformDocApiDocTemplateParamFieldDO openplatformDocApiDocTemplateParamFieldDO = OpenplatformDocApiDocTemplateParamFieldInfrastructureStructMapping.instance.openplatformDocApiDocTemplateParamFieldToOpenplatformDocApiDocTemplateParamFieldDO(openplatformDocApiDocTemplateParamField);
		if (openplatformDocApiDocTemplateParamFieldDO.getId() == null) {
			openplatformDocApiDocTemplateParamFieldDO.setAddControl(openplatformDocApiDocTemplateParamField.getAddControl());
			OpenplatformDocApiDocTemplateParamFieldDO add = iOpenplatformDocApiDocTemplateParamFieldService.add(openplatformDocApiDocTemplateParamFieldDO);
			openplatformDocApiDocTemplateParamField.setId(OpenplatformDocApiDocTemplateParamFieldId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocTemplateParamFieldDO.setUpdateControl(openplatformDocApiDocTemplateParamField.getUpdateControl());
		OpenplatformDocApiDocTemplateParamFieldDO update = iOpenplatformDocApiDocTemplateParamFieldService.update(openplatformDocApiDocTemplateParamFieldDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateParamFieldId openplatformDocApiDocTemplateParamFieldId) {
		return iOpenplatformDocApiDocTemplateParamFieldService.deleteById(openplatformDocApiDocTemplateParamFieldId.getId());
	}


	@Autowired
	public void setIOpenplatformDocApiDocTemplateParamFieldService(IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService) {
		this.iOpenplatformDocApiDocTemplateParamFieldService = iOpenplatformDocApiDocTemplateParamFieldService;
	}
}

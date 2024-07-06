package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplate;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocTemplateInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档模板 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Component
public class OpenplatformDocApiDocTemplateGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocTemplateId,OpenplatformDocApiDocTemplate> implements OpenplatformDocApiDocTemplateGateway {

	private IOpenplatformDocApiDocTemplateService iOpenplatformDocApiDocTemplateService;

	@Override
	public OpenplatformDocApiDocTemplate getById(OpenplatformDocApiDocTemplateId openplatformDocApiDocTemplateId) {
		OpenplatformDocApiDocTemplateDO byId = iOpenplatformDocApiDocTemplateService.getById(openplatformDocApiDocTemplateId.getId());
		OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate = DomainFactory.create(OpenplatformDocApiDocTemplate.class);
		openplatformDocApiDocTemplate = OpenplatformDocApiDocTemplateInfrastructureStructMapping.instance. openplatformDocApiDocTemplateDOToOpenplatformDocApiDocTemplate(openplatformDocApiDocTemplate,byId);
		return openplatformDocApiDocTemplate;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocTemplate openplatformDocApiDocTemplate) {
		OpenplatformDocApiDocTemplateDO openplatformDocApiDocTemplateDO = OpenplatformDocApiDocTemplateInfrastructureStructMapping.instance.openplatformDocApiDocTemplateToOpenplatformDocApiDocTemplateDO(openplatformDocApiDocTemplate);
		if (openplatformDocApiDocTemplateDO.getId() == null) {
			openplatformDocApiDocTemplateDO.setAddControl(openplatformDocApiDocTemplate.getAddControl());
			OpenplatformDocApiDocTemplateDO add = iOpenplatformDocApiDocTemplateService.add(openplatformDocApiDocTemplateDO);
			openplatformDocApiDocTemplate.setId(OpenplatformDocApiDocTemplateId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocTemplateDO.setUpdateControl(openplatformDocApiDocTemplate.getUpdateControl());
		OpenplatformDocApiDocTemplateDO update = iOpenplatformDocApiDocTemplateService.update(openplatformDocApiDocTemplateDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateId openplatformDocApiDocTemplateId) {
		return iOpenplatformDocApiDocTemplateService.deleteById(openplatformDocApiDocTemplateId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateId id, IdCommand idCommand) {
		return iOpenplatformDocApiDocTemplateService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateService(IOpenplatformDocApiDocTemplateService iOpenplatformDocApiDocTemplateService) {
		this.iOpenplatformDocApiDocTemplateService = iOpenplatformDocApiDocTemplateService;
	}
}

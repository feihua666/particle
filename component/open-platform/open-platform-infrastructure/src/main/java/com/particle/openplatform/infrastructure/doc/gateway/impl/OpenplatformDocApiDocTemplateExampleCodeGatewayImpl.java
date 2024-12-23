package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateExampleCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateExampleCodeGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateExampleCodeService;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档模板示例代码 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:26
 */
@Component
public class OpenplatformDocApiDocTemplateExampleCodeGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocTemplateExampleCodeId,OpenplatformDocApiDocTemplateExampleCode> implements OpenplatformDocApiDocTemplateExampleCodeGateway {

	private IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService;

	@Override
	public OpenplatformDocApiDocTemplateExampleCode getById(OpenplatformDocApiDocTemplateExampleCodeId openplatformDocApiDocTemplateExampleCodeId) {
		OpenplatformDocApiDocTemplateExampleCodeDO byId = iOpenplatformDocApiDocTemplateExampleCodeService.getById(openplatformDocApiDocTemplateExampleCodeId.getId());
		OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode = DomainFactory.create(OpenplatformDocApiDocTemplateExampleCode.class);
		openplatformDocApiDocTemplateExampleCode = OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping.instance. openplatformDocApiDocTemplateExampleCodeDOToOpenplatformDocApiDocTemplateExampleCode(openplatformDocApiDocTemplateExampleCode,byId);
		return openplatformDocApiDocTemplateExampleCode;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocTemplateExampleCode openplatformDocApiDocTemplateExampleCode) {
		OpenplatformDocApiDocTemplateExampleCodeDO openplatformDocApiDocTemplateExampleCodeDO = OpenplatformDocApiDocTemplateExampleCodeInfrastructureStructMapping.instance.openplatformDocApiDocTemplateExampleCodeToOpenplatformDocApiDocTemplateExampleCodeDO(openplatformDocApiDocTemplateExampleCode);
		if (openplatformDocApiDocTemplateExampleCodeDO.getId() == null) {
			openplatformDocApiDocTemplateExampleCodeDO.setAddControl(openplatformDocApiDocTemplateExampleCode.getAddControl());
			OpenplatformDocApiDocTemplateExampleCodeDO add = iOpenplatformDocApiDocTemplateExampleCodeService.add(openplatformDocApiDocTemplateExampleCodeDO);
			openplatformDocApiDocTemplateExampleCode.setId(OpenplatformDocApiDocTemplateExampleCodeId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocTemplateExampleCodeDO.setUpdateControl(openplatformDocApiDocTemplateExampleCode.getUpdateControl());
		OpenplatformDocApiDocTemplateExampleCodeDO update = iOpenplatformDocApiDocTemplateExampleCodeService.update(openplatformDocApiDocTemplateExampleCodeDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateExampleCodeId openplatformDocApiDocTemplateExampleCodeId) {
		return iOpenplatformDocApiDocTemplateExampleCodeService.deleteById(openplatformDocApiDocTemplateExampleCodeId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateExampleCodeId id, IdCommand idCommand) {
		return iOpenplatformDocApiDocTemplateExampleCodeService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateExampleCodeService(IOpenplatformDocApiDocTemplateExampleCodeService iOpenplatformDocApiDocTemplateExampleCodeService) {
		this.iOpenplatformDocApiDocTemplateExampleCodeService = iOpenplatformDocApiDocTemplateExampleCodeService;
	}
}

package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocTemplateResponseCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocTemplateResponseCodeGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateResponseCodeService;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档模板响应码 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-18 16:49:10
 */
@Component
public class OpenplatformDocApiDocTemplateResponseCodeGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocTemplateResponseCodeId,OpenplatformDocApiDocTemplateResponseCode> implements OpenplatformDocApiDocTemplateResponseCodeGateway {

	private IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService;

	@Override
	public OpenplatformDocApiDocTemplateResponseCode getById(OpenplatformDocApiDocTemplateResponseCodeId openplatformDocApiDocTemplateResponseCodeId) {
		OpenplatformDocApiDocTemplateResponseCodeDO byId = iOpenplatformDocApiDocTemplateResponseCodeService.getById(openplatformDocApiDocTemplateResponseCodeId.getId());
		OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode = DomainFactory.create(OpenplatformDocApiDocTemplateResponseCode.class);
		openplatformDocApiDocTemplateResponseCode = OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping.instance. openplatformDocApiDocTemplateResponseCodeDOToOpenplatformDocApiDocTemplateResponseCode(openplatformDocApiDocTemplateResponseCode,byId);
		return openplatformDocApiDocTemplateResponseCode;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocTemplateResponseCode openplatformDocApiDocTemplateResponseCode) {
		OpenplatformDocApiDocTemplateResponseCodeDO openplatformDocApiDocTemplateResponseCodeDO = OpenplatformDocApiDocTemplateResponseCodeInfrastructureStructMapping.instance.openplatformDocApiDocTemplateResponseCodeToOpenplatformDocApiDocTemplateResponseCodeDO(openplatformDocApiDocTemplateResponseCode);
		if (openplatformDocApiDocTemplateResponseCodeDO.getId() == null) {
			openplatformDocApiDocTemplateResponseCodeDO.setAddControl(openplatformDocApiDocTemplateResponseCode.getAddControl());
			OpenplatformDocApiDocTemplateResponseCodeDO add = iOpenplatformDocApiDocTemplateResponseCodeService.add(openplatformDocApiDocTemplateResponseCodeDO);
			openplatformDocApiDocTemplateResponseCode.setId(OpenplatformDocApiDocTemplateResponseCodeId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocTemplateResponseCodeDO.setUpdateControl(openplatformDocApiDocTemplateResponseCode.getUpdateControl());
		OpenplatformDocApiDocTemplateResponseCodeDO update = iOpenplatformDocApiDocTemplateResponseCodeService.update(openplatformDocApiDocTemplateResponseCodeDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateResponseCodeId openplatformDocApiDocTemplateResponseCodeId) {
		return iOpenplatformDocApiDocTemplateResponseCodeService.deleteById(openplatformDocApiDocTemplateResponseCodeId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiDocTemplateResponseCodeId id, IdCommand idCommand) {
		return iOpenplatformDocApiDocTemplateResponseCodeService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateResponseCodeService(IOpenplatformDocApiDocTemplateResponseCodeService iOpenplatformDocApiDocTemplateResponseCodeService) {
		this.iOpenplatformDocApiDocTemplateResponseCodeService = iOpenplatformDocApiDocTemplateResponseCodeService;
	}
}

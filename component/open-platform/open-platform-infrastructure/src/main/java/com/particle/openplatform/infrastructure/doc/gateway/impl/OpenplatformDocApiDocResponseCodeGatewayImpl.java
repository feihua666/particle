package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocResponseCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocResponseCodeGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocResponseCodeService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocResponseCodeDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocResponseCodeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档响应码 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-18 14:44:43
 */
@Component
public class OpenplatformDocApiDocResponseCodeGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocResponseCodeId,OpenplatformDocApiDocResponseCode> implements OpenplatformDocApiDocResponseCodeGateway {

	private IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService;

	@Override
	public OpenplatformDocApiDocResponseCode getById(OpenplatformDocApiDocResponseCodeId openplatformDocApiDocResponseCodeId) {
		OpenplatformDocApiDocResponseCodeDO byId = iOpenplatformDocApiDocResponseCodeService.getById(openplatformDocApiDocResponseCodeId.getId());
		OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode = DomainFactory.create(OpenplatformDocApiDocResponseCode.class);
		openplatformDocApiDocResponseCode = OpenplatformDocApiDocResponseCodeInfrastructureStructMapping.instance. openplatformDocApiDocResponseCodeDOToOpenplatformDocApiDocResponseCode(openplatformDocApiDocResponseCode,byId);
		return openplatformDocApiDocResponseCode;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocResponseCode openplatformDocApiDocResponseCode) {
		OpenplatformDocApiDocResponseCodeDO openplatformDocApiDocResponseCodeDO = OpenplatformDocApiDocResponseCodeInfrastructureStructMapping.instance.openplatformDocApiDocResponseCodeToOpenplatformDocApiDocResponseCodeDO(openplatformDocApiDocResponseCode);
		if (openplatformDocApiDocResponseCodeDO.getId() == null) {
			openplatformDocApiDocResponseCodeDO.setAddControl(openplatformDocApiDocResponseCode.getAddControl());
			OpenplatformDocApiDocResponseCodeDO add = iOpenplatformDocApiDocResponseCodeService.add(openplatformDocApiDocResponseCodeDO);
			openplatformDocApiDocResponseCode.setId(OpenplatformDocApiDocResponseCodeId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocResponseCodeDO.setUpdateControl(openplatformDocApiDocResponseCode.getUpdateControl());
		OpenplatformDocApiDocResponseCodeDO update = iOpenplatformDocApiDocResponseCodeService.update(openplatformDocApiDocResponseCodeDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocResponseCodeId openplatformDocApiDocResponseCodeId) {
		return iOpenplatformDocApiDocResponseCodeService.deleteById(openplatformDocApiDocResponseCodeId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiDocResponseCodeId id, IdCommand idCommand) {
		return iOpenplatformDocApiDocResponseCodeService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiDocResponseCodeService(IOpenplatformDocApiDocResponseCodeService iOpenplatformDocApiDocResponseCodeService) {
		this.iOpenplatformDocApiDocResponseCodeService = iOpenplatformDocApiDocResponseCodeService;
	}
}

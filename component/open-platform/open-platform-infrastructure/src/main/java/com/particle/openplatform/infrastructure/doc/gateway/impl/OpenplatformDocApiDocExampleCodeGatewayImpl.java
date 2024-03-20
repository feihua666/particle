package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCode;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocExampleCodeId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocExampleCodeGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocExampleCodeService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocExampleCodeDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocExampleCodeInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档示例代码 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-18 17:04:11
 */
@Component
public class OpenplatformDocApiDocExampleCodeGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocExampleCodeId,OpenplatformDocApiDocExampleCode> implements OpenplatformDocApiDocExampleCodeGateway {

	private IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService;

	@Override
	public OpenplatformDocApiDocExampleCode getById(OpenplatformDocApiDocExampleCodeId openplatformDocApiDocExampleCodeId) {
		OpenplatformDocApiDocExampleCodeDO byId = iOpenplatformDocApiDocExampleCodeService.getById(openplatformDocApiDocExampleCodeId.getId());
		OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode = DomainFactory.create(OpenplatformDocApiDocExampleCode.class);
		openplatformDocApiDocExampleCode = OpenplatformDocApiDocExampleCodeInfrastructureStructMapping.instance. openplatformDocApiDocExampleCodeDOToOpenplatformDocApiDocExampleCode(openplatformDocApiDocExampleCode,byId);
		return openplatformDocApiDocExampleCode;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDocExampleCode openplatformDocApiDocExampleCode) {
		OpenplatformDocApiDocExampleCodeDO openplatformDocApiDocExampleCodeDO = OpenplatformDocApiDocExampleCodeInfrastructureStructMapping.instance.openplatformDocApiDocExampleCodeToOpenplatformDocApiDocExampleCodeDO(openplatformDocApiDocExampleCode);
		if (openplatformDocApiDocExampleCodeDO.getId() == null) {
			openplatformDocApiDocExampleCodeDO.setAddControl(openplatformDocApiDocExampleCode.getAddControl());
			OpenplatformDocApiDocExampleCodeDO add = iOpenplatformDocApiDocExampleCodeService.add(openplatformDocApiDocExampleCodeDO);
			openplatformDocApiDocExampleCode.setId(OpenplatformDocApiDocExampleCodeId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocExampleCodeDO.setUpdateControl(openplatformDocApiDocExampleCode.getUpdateControl());
		OpenplatformDocApiDocExampleCodeDO update = iOpenplatformDocApiDocExampleCodeService.update(openplatformDocApiDocExampleCodeDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocExampleCodeId openplatformDocApiDocExampleCodeId) {
		return iOpenplatformDocApiDocExampleCodeService.deleteById(openplatformDocApiDocExampleCodeId.getId());
	}


	@Autowired
	public void setIOpenplatformDocApiDocExampleCodeService(IOpenplatformDocApiDocExampleCodeService iOpenplatformDocApiDocExampleCodeService) {
		this.iOpenplatformDocApiDocExampleCodeService = iOpenplatformDocApiDocExampleCodeService;
	}
}

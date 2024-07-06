package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRel;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDirRelId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDirRelGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDirRelService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDirRelDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDirRelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档接口与目录关系 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:20
 */
@Component
public class OpenplatformDocApiDirRelGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDirRelId,OpenplatformDocApiDirRel> implements OpenplatformDocApiDirRelGateway {

	private IOpenplatformDocApiDirRelService iOpenplatformDocApiDirRelService;

	@Override
	public OpenplatformDocApiDirRel getById(OpenplatformDocApiDirRelId openplatformDocApiDirRelId) {
		OpenplatformDocApiDirRelDO byId = iOpenplatformDocApiDirRelService.getById(openplatformDocApiDirRelId.getId());
		OpenplatformDocApiDirRel openplatformDocApiDirRel = DomainFactory.create(OpenplatformDocApiDirRel.class);
		openplatformDocApiDirRel = OpenplatformDocApiDirRelInfrastructureStructMapping.instance. openplatformDocApiDirRelDOToOpenplatformDocApiDirRel(openplatformDocApiDirRel,byId);
		return openplatformDocApiDirRel;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDirRel openplatformDocApiDirRel) {
		OpenplatformDocApiDirRelDO openplatformDocApiDirRelDO = OpenplatformDocApiDirRelInfrastructureStructMapping.instance.openplatformDocApiDirRelToOpenplatformDocApiDirRelDO(openplatformDocApiDirRel);
		if (openplatformDocApiDirRelDO.getId() == null) {
			openplatformDocApiDirRelDO.setAddControl(openplatformDocApiDirRel.getAddControl());
			OpenplatformDocApiDirRelDO add = iOpenplatformDocApiDirRelService.add(openplatformDocApiDirRelDO);
			openplatformDocApiDirRel.setId(OpenplatformDocApiDirRelId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDirRelDO.setUpdateControl(openplatformDocApiDirRel.getUpdateControl());
		OpenplatformDocApiDirRelDO update = iOpenplatformDocApiDirRelService.update(openplatformDocApiDirRelDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDirRelId openplatformDocApiDirRelId) {
		return iOpenplatformDocApiDirRelService.deleteById(openplatformDocApiDirRelId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiDirRelId id, IdCommand idCommand) {
		return iOpenplatformDocApiDirRelService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiDirRelService(IOpenplatformDocApiDirRelService iOpenplatformDocApiDirRelService) {
		this.iOpenplatformDocApiDirRelService = iOpenplatformDocApiDirRelService;
	}
}

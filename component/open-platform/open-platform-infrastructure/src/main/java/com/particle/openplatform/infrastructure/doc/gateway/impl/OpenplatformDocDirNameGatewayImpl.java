package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocDirName;
import com.particle.openplatform.domain.doc.OpenplatformDocDirNameId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirNameGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirNameService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirNameDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocDirNameInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口目录名称 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:53:48
 */
@Component
public class OpenplatformDocDirNameGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocDirNameId,OpenplatformDocDirName> implements OpenplatformDocDirNameGateway {

	private IOpenplatformDocDirNameService iOpenplatformDocDirNameService;

	@Override
	public OpenplatformDocDirName getById(OpenplatformDocDirNameId openplatformDocDirNameId) {
		OpenplatformDocDirNameDO byId = iOpenplatformDocDirNameService.getById(openplatformDocDirNameId.getId());
		OpenplatformDocDirName openplatformDocDirName = DomainFactory.create(OpenplatformDocDirName.class);
		openplatformDocDirName = OpenplatformDocDirNameInfrastructureStructMapping.instance. openplatformDocDirNameDOToOpenplatformDocDirName(openplatformDocDirName,byId);
		return openplatformDocDirName;
	}

	@Override
	public boolean doSave(OpenplatformDocDirName openplatformDocDirName) {
		OpenplatformDocDirNameDO openplatformDocDirNameDO = OpenplatformDocDirNameInfrastructureStructMapping.instance.openplatformDocDirNameToOpenplatformDocDirNameDO(openplatformDocDirName);
		if (openplatformDocDirNameDO.getId() == null) {
			openplatformDocDirNameDO.setAddControl(openplatformDocDirName.getAddControl());
			OpenplatformDocDirNameDO add = iOpenplatformDocDirNameService.add(openplatformDocDirNameDO);
			openplatformDocDirName.setId(OpenplatformDocDirNameId.of(add.getId()));
			return add != null;
		}
		openplatformDocDirNameDO.setUpdateControl(openplatformDocDirName.getUpdateControl());
		OpenplatformDocDirNameDO update = iOpenplatformDocDirNameService.update(openplatformDocDirNameDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocDirNameId openplatformDocDirNameId) {
		return iOpenplatformDocDirNameService.deleteById(openplatformDocDirNameId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocDirNameId id, IdCommand idCommand) {
		return iOpenplatformDocDirNameService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocDirNameService(IOpenplatformDocDirNameService iOpenplatformDocDirNameService) {
		this.iOpenplatformDocDirNameService = iOpenplatformDocDirNameService;
	}
}

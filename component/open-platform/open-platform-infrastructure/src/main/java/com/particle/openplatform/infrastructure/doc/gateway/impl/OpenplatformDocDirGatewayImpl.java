package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocDir;
import com.particle.openplatform.domain.doc.OpenplatformDocDirId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocDirGateway;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocDirService;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocDirDO;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocDirInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口目录 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:55:42
 */
@Component
public class OpenplatformDocDirGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocDirId,OpenplatformDocDir> implements OpenplatformDocDirGateway {

	private IOpenplatformDocDirService iOpenplatformDocDirService;

	@Override
	public OpenplatformDocDir getById(OpenplatformDocDirId openplatformDocDirId) {
		OpenplatformDocDirDO byId = iOpenplatformDocDirService.getById(openplatformDocDirId.getId());
		OpenplatformDocDir openplatformDocDir = DomainFactory.create(OpenplatformDocDir.class);
		openplatformDocDir = OpenplatformDocDirInfrastructureStructMapping.instance. openplatformDocDirDOToOpenplatformDocDir(openplatformDocDir,byId);
		return openplatformDocDir;
	}

	@Override
	public boolean doSave(OpenplatformDocDir openplatformDocDir) {
		OpenplatformDocDirDO openplatformDocDirDO = OpenplatformDocDirInfrastructureStructMapping.instance.openplatformDocDirToOpenplatformDocDirDO(openplatformDocDir);
		if (openplatformDocDirDO.getId() == null) {
			openplatformDocDirDO.setAddControl(openplatformDocDir.getAddControl());
			OpenplatformDocDirDO add = iOpenplatformDocDirService.add(openplatformDocDirDO);
			openplatformDocDir.setId(OpenplatformDocDirId.of(add.getId()));
			return add != null;
		}
		openplatformDocDirDO.setUpdateControl(openplatformDocDir.getUpdateControl());
		OpenplatformDocDirDO update = iOpenplatformDocDirService.update(openplatformDocDirDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocDirId openplatformDocDirId) {
		return iOpenplatformDocDirService.deleteById(openplatformDocDirId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocDirId id, IdCommand idCommand) {
		return iOpenplatformDocDirService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocDirService(IOpenplatformDocDirService iOpenplatformDocDirService) {
		this.iOpenplatformDocDirService = iOpenplatformDocDirService;
	}
}

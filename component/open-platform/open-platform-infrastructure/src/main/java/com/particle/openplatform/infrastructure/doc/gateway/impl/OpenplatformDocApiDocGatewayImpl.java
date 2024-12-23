package com.particle.openplatform.infrastructure.doc.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDoc;
import com.particle.openplatform.domain.doc.OpenplatformDocApiDocId;
import com.particle.openplatform.domain.doc.gateway.OpenplatformDocApiDocGateway;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocService;
import com.particle.openplatform.infrastructure.doc.structmapping.OpenplatformDocApiDocInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放接口文档 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-03-14 14:56:37
 */
@Component
public class OpenplatformDocApiDocGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformDocApiDocId,OpenplatformDocApiDoc> implements OpenplatformDocApiDocGateway {

	private IOpenplatformDocApiDocService iOpenplatformDocApiDocService;

	@Override
	public OpenplatformDocApiDoc getById(OpenplatformDocApiDocId openplatformDocApiDocId) {
		OpenplatformDocApiDocDO byId = iOpenplatformDocApiDocService.getById(openplatformDocApiDocId.getId());
		OpenplatformDocApiDoc openplatformDocApiDoc = DomainFactory.create(OpenplatformDocApiDoc.class);
		openplatformDocApiDoc = OpenplatformDocApiDocInfrastructureStructMapping.instance. openplatformDocApiDocDOToOpenplatformDocApiDoc(openplatformDocApiDoc,byId);
		return openplatformDocApiDoc;
	}

	@Override
	public boolean doSave(OpenplatformDocApiDoc openplatformDocApiDoc) {
		OpenplatformDocApiDocDO openplatformDocApiDocDO = OpenplatformDocApiDocInfrastructureStructMapping.instance.openplatformDocApiDocToOpenplatformDocApiDocDO(openplatformDocApiDoc);
		if (openplatformDocApiDocDO.getId() == null) {
			openplatformDocApiDocDO.setAddControl(openplatformDocApiDoc.getAddControl());
			OpenplatformDocApiDocDO add = iOpenplatformDocApiDocService.add(openplatformDocApiDocDO);
			openplatformDocApiDoc.setId(OpenplatformDocApiDocId.of(add.getId()));
			return add != null;
		}
		openplatformDocApiDocDO.setUpdateControl(openplatformDocApiDoc.getUpdateControl());
		OpenplatformDocApiDocDO update = iOpenplatformDocApiDocService.update(openplatformDocApiDocDO);
		return update != null;
	}

	@Override
	public boolean delete(OpenplatformDocApiDocId openplatformDocApiDocId) {
		return iOpenplatformDocApiDocService.deleteById(openplatformDocApiDocId.getId());
	}

	@Override
	public boolean delete(OpenplatformDocApiDocId id, IdCommand idCommand) {
		return iOpenplatformDocApiDocService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpenplatformDocApiDocService(IOpenplatformDocApiDocService iOpenplatformDocApiDocService) {
		this.iOpenplatformDocApiDocService = iOpenplatformDocApiDocService;
	}
}

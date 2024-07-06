package com.particle.oplog.infrastructure.gateway.impl;

import com.particle.global.dto.basic.IdCommand;
import com.particle.oplog.domain.OpLogAuditData;
import com.particle.oplog.domain.OpLogAuditDataId;
import com.particle.oplog.domain.gateway.OpLogAuditDataGateway;
import com.particle.oplog.infrastructure.service.IOpLogAuditDataService;
import com.particle.oplog.infrastructure.dos.OpLogAuditDataDO;
import com.particle.oplog.infrastructure.structmapping.OpLogAuditDataInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 操作日志审计数据 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:33:30
 */
@Component
public class OpLogAuditDataGatewayImpl extends AbstractBaseGatewayImpl<OpLogAuditDataId,OpLogAuditData> implements OpLogAuditDataGateway {

	private IOpLogAuditDataService iOpLogAuditDataService;

	@Override
	public OpLogAuditData getById(OpLogAuditDataId opLogAuditDataId) {
		OpLogAuditDataDO byId = iOpLogAuditDataService.getById(opLogAuditDataId.getId());
		OpLogAuditData opLogAuditData = DomainFactory.create(OpLogAuditData.class);
		opLogAuditData = OpLogAuditDataInfrastructureStructMapping.instance. opLogAuditDataDOToOpLogAuditData(opLogAuditData,byId);
		return opLogAuditData;
	}

	@Override
	public boolean doSave(OpLogAuditData opLogAuditData) {
		OpLogAuditDataDO opLogAuditDataDO = OpLogAuditDataInfrastructureStructMapping.instance.opLogAuditDataToOpLogAuditDataDO(opLogAuditData);
		if (opLogAuditDataDO.getId() == null) {
			opLogAuditDataDO.setAddControl(opLogAuditData.getAddControl());
			OpLogAuditDataDO add = iOpLogAuditDataService.add(opLogAuditDataDO);
			opLogAuditData.setId(OpLogAuditDataId.of(add.getId()));
			return add != null;
		}
		opLogAuditDataDO.setUpdateControl(opLogAuditData.getUpdateControl());
		OpLogAuditDataDO update = iOpLogAuditDataService.update(opLogAuditDataDO);
		return update != null;
	}

	@Override
	public boolean delete(OpLogAuditDataId opLogAuditDataId) {
		return iOpLogAuditDataService.deleteById(opLogAuditDataId.getId());
	}

	@Override
	public boolean delete(OpLogAuditDataId id, IdCommand idCommand) {
		return iOpLogAuditDataService.deleteById(idCommand);
	}

	@Autowired
	public void setIOpLogAuditDataService(IOpLogAuditDataService iOpLogAuditDataService) {
		this.iOpLogAuditDataService = iOpLogAuditDataService;
	}
}

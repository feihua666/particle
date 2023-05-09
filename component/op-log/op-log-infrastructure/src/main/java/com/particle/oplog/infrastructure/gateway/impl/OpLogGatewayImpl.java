package com.particle.oplog.infrastructure.gateway.impl;

import com.particle.oplog.domain.OpLog;
import com.particle.oplog.domain.OpLogId;
import com.particle.oplog.domain.gateway.OpLogGateway;
import com.particle.oplog.infrastructure.service.IOpLogService;
import com.particle.oplog.infrastructure.dos.OpLogDO;
import com.particle.oplog.infrastructure.structmapping.OpLogInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 操作日志 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2023-05-08 18:32:34
 */
@Component
public class OpLogGatewayImpl extends AbstractBaseGatewayImpl<OpLogId,OpLog> implements OpLogGateway {

	private IOpLogService iOpLogService;

	@Override
	public OpLog getById(OpLogId opLogId) {
		OpLogDO byId = iOpLogService.getById(opLogId.getId());
		OpLog opLog = DomainFactory.create(OpLog.class);
		opLog = OpLogInfrastructureStructMapping.instance. opLogDOToOpLog(opLog,byId);
		return opLog;
	}

	@Override
	public boolean doSave(OpLog opLog) {
		OpLogDO opLogDO = OpLogInfrastructureStructMapping.instance.opLogToOpLogDO(opLog);
		if (opLogDO.getId() == null) {
			opLogDO.setAddControl(opLog.getAddControl());
			OpLogDO add = iOpLogService.add(opLogDO);
			opLog.setId(OpLogId.of(add.getId()));
			return add != null;
		}
		opLogDO.setUpdateControl(opLog.getUpdateControl());
		OpLogDO update = iOpLogService.update(opLogDO);
		return update != null;
	}

	@Override
	public boolean delete(OpLogId opLogId) {
		return iOpLogService.deleteById(opLogId.getId());
	}


	@Autowired
	public void setIOpLogService(IOpLogService iOpLogService) {
		this.iOpLogService = iOpLogService;
	}
}

package com.particle.oplog.infrastructure.error.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.oplog.domain.error.OpLogError;
import com.particle.oplog.domain.error.OpLogErrorId;
import com.particle.oplog.domain.error.gateway.OpLogErrorGateway;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorDO;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorService;
import com.particle.oplog.infrastructure.error.structmapping.OpLogErrorInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 操作异常日志 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:09
 */
@Component
public class OpLogErrorGatewayImpl extends AbstractBaseGatewayImpl<OpLogErrorId,OpLogError> implements OpLogErrorGateway {

    private IOpLogErrorService iOpLogErrorService;

    @Override
    public OpLogError getById(OpLogErrorId opLogErrorId) {
        OpLogErrorDO byId = iOpLogErrorService.getById(opLogErrorId.getId());
        OpLogError opLogError = DomainFactory.create(OpLogError.class);
        opLogError = OpLogErrorInfrastructureStructMapping.instance. opLogErrorDOToOpLogError(opLogError,byId);
        return opLogError;
    }

    @Override
    public boolean doSave(OpLogError opLogError) {
        OpLogErrorDO opLogErrorDO = OpLogErrorInfrastructureStructMapping.instance.opLogErrorToOpLogErrorDO(opLogError);
        if (opLogErrorDO.getId() == null) {
            opLogErrorDO.setAddControl(opLogError.getAddControl());
            OpLogErrorDO add = iOpLogErrorService.add(opLogErrorDO);
            opLogError.setId(OpLogErrorId.of(add.getId()));
            return add != null;
        }
        opLogErrorDO.setUpdateControl(opLogError.getUpdateControl());
        OpLogErrorDO update = iOpLogErrorService.update(opLogErrorDO);
        return update != null;
    }

    @Override
    public boolean delete(OpLogErrorId opLogErrorId) {
        return iOpLogErrorService.deleteById(opLogErrorId.getId());
    }

    @Override
    public boolean delete(OpLogErrorId opLogErrorId, IdCommand idCommand) {
        return iOpLogErrorService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpLogErrorService(IOpLogErrorService iOpLogErrorService) {
        this.iOpLogErrorService = iOpLogErrorService;
    }
}

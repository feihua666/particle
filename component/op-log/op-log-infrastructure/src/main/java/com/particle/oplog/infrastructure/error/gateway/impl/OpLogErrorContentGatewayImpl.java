package com.particle.oplog.infrastructure.error.gateway.impl;

import com.particle.oplog.domain.error.OpLogErrorContent;
import com.particle.oplog.domain.error.OpLogErrorContentId;
import com.particle.oplog.domain.error.gateway.OpLogErrorContentGateway;
import com.particle.oplog.infrastructure.error.service.IOpLogErrorContentService;
import com.particle.oplog.infrastructure.error.dos.OpLogErrorContentDO;
import com.particle.oplog.infrastructure.error.structmapping.OpLogErrorContentInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 操作异常日志内容 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-08-09 14:19:59
 */
@Component
public class OpLogErrorContentGatewayImpl extends AbstractBaseGatewayImpl<OpLogErrorContentId,OpLogErrorContent> implements OpLogErrorContentGateway {

    private IOpLogErrorContentService iOpLogErrorContentService;

    @Override
    public OpLogErrorContent getById(OpLogErrorContentId opLogErrorContentId) {
        OpLogErrorContentDO byId = iOpLogErrorContentService.getById(opLogErrorContentId.getId());
        OpLogErrorContent opLogErrorContent = DomainFactory.create(OpLogErrorContent.class);
        opLogErrorContent = OpLogErrorContentInfrastructureStructMapping.instance. opLogErrorContentDOToOpLogErrorContent(opLogErrorContent,byId);
        return opLogErrorContent;
    }

    @Override
    public boolean doSave(OpLogErrorContent opLogErrorContent) {
        OpLogErrorContentDO opLogErrorContentDO = OpLogErrorContentInfrastructureStructMapping.instance.opLogErrorContentToOpLogErrorContentDO(opLogErrorContent);
        if (opLogErrorContentDO.getId() == null) {
            opLogErrorContentDO.setAddControl(opLogErrorContent.getAddControl());
            OpLogErrorContentDO add = iOpLogErrorContentService.add(opLogErrorContentDO);
            opLogErrorContent.setId(OpLogErrorContentId.of(add.getId()));
            return add != null;
        }
        opLogErrorContentDO.setUpdateControl(opLogErrorContent.getUpdateControl());
        OpLogErrorContentDO update = iOpLogErrorContentService.update(opLogErrorContentDO);
        return update != null;
    }

    @Override
    public boolean delete(OpLogErrorContentId opLogErrorContentId) {
        return iOpLogErrorContentService.deleteById(opLogErrorContentId.getId());
    }

    @Override
    public boolean delete(OpLogErrorContentId opLogErrorContentId, IdCommand idCommand) {
        return iOpLogErrorContentService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpLogErrorContentService(IOpLogErrorContentService iOpLogErrorContentService) {
        this.iOpLogErrorContentService = iOpLogErrorContentService;
    }
}

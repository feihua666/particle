package com.particle.agi.infrastructure.model.gateway.impl;

import com.particle.agi.domain.model.AgiAiModel;
import com.particle.agi.domain.model.AgiAiModelId;
import com.particle.agi.domain.model.gateway.AgiAiModelGateway;
import com.particle.agi.infrastructure.model.service.IAgiAiModelService;
import com.particle.agi.infrastructure.model.dos.AgiAiModelDO;
import com.particle.agi.infrastructure.model.structmapping.AgiAiModelInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * AI模型 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:22:25
 */
@Component
public class AgiAiModelGatewayImpl extends AbstractBaseGatewayImpl<AgiAiModelId,AgiAiModel> implements AgiAiModelGateway {

    private IAgiAiModelService iAgiAiModelService;

    @Override
    public AgiAiModel getById(AgiAiModelId agiAiModelId) {
        AgiAiModelDO byId = iAgiAiModelService.getById(agiAiModelId.getId());
        AgiAiModel agiAiModel = DomainFactory.create(AgiAiModel.class);
        agiAiModel = AgiAiModelInfrastructureStructMapping.instance. agiAiModelDOToAgiAiModel(agiAiModel,byId);
        return agiAiModel;
    }

    @Override
    public boolean doSave(AgiAiModel agiAiModel) {
        AgiAiModelDO agiAiModelDO = AgiAiModelInfrastructureStructMapping.instance.agiAiModelToAgiAiModelDO(agiAiModel);
        if (agiAiModelDO.getId() == null) {
            agiAiModelDO.setAddControl(agiAiModel.getAddControl());
            AgiAiModelDO add = iAgiAiModelService.add(agiAiModelDO);
            agiAiModel.setId(AgiAiModelId.of(add.getId()));
            return add != null;
        }
        agiAiModelDO.setUpdateControl(agiAiModel.getUpdateControl());
        AgiAiModelDO update = iAgiAiModelService.update(agiAiModelDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiAiModelId agiAiModelId) {
        return iAgiAiModelService.deleteById(agiAiModelId.getId());
    }

    @Override
    public boolean delete(AgiAiModelId agiAiModelId, IdCommand idCommand) {
        return iAgiAiModelService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiAiModelService(IAgiAiModelService iAgiAiModelService) {
        this.iAgiAiModelService = iAgiAiModelService;
    }
}

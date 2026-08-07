package com.particle.agi.infrastructure.model.gateway.impl;

import com.particle.agi.domain.model.AgiModelProvider;
import com.particle.agi.domain.model.AgiModelProviderId;
import com.particle.agi.domain.model.gateway.AgiModelProviderGateway;
import com.particle.agi.infrastructure.model.service.IAgiModelProviderService;
import com.particle.agi.infrastructure.model.dos.AgiModelProviderDO;
import com.particle.agi.infrastructure.model.structmapping.AgiModelProviderInfrastructureStructMapping;
import com.particle.global.domain.DomainFactory;
import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.dto.basic.IdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * AI模型提供商 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2026-04-16 14:23:16
 */
@Component
public class AgiModelProviderGatewayImpl extends AbstractBaseGatewayImpl<AgiModelProviderId,AgiModelProvider> implements AgiModelProviderGateway {

    private IAgiModelProviderService iAgiModelProviderService;

    @Override
    public AgiModelProvider getById(AgiModelProviderId agiModelProviderId) {
        AgiModelProviderDO byId = iAgiModelProviderService.getById(agiModelProviderId.getId());
        AgiModelProvider agiModelProvider = DomainFactory.create(AgiModelProvider.class);
        agiModelProvider = AgiModelProviderInfrastructureStructMapping.instance. agiModelProviderDOToAgiModelProvider(agiModelProvider,byId);
        return agiModelProvider;
    }

    @Override
    public boolean doSave(AgiModelProvider agiModelProvider) {
        AgiModelProviderDO agiModelProviderDO = AgiModelProviderInfrastructureStructMapping.instance.agiModelProviderToAgiModelProviderDO(agiModelProvider);
        if (agiModelProviderDO.getId() == null) {
            agiModelProviderDO.setAddControl(agiModelProvider.getAddControl());
            AgiModelProviderDO add = iAgiModelProviderService.add(agiModelProviderDO);
            agiModelProvider.setId(AgiModelProviderId.of(add.getId()));
            return add != null;
        }
        agiModelProviderDO.setUpdateControl(agiModelProvider.getUpdateControl());
        AgiModelProviderDO update = iAgiModelProviderService.update(agiModelProviderDO);
        return update != null;
    }

    @Override
    public boolean delete(AgiModelProviderId agiModelProviderId) {
        return iAgiModelProviderService.deleteById(agiModelProviderId.getId());
    }

    @Override
    public boolean delete(AgiModelProviderId agiModelProviderId, IdCommand idCommand) {
        return iAgiModelProviderService.deleteById(idCommand);
    }

    @Autowired
    public void setIAgiModelProviderService(IAgiModelProviderService iAgiModelProviderService) {
        this.iAgiModelProviderService = iAgiModelProviderService;
    }
}

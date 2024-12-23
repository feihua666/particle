package com.particle.openplatform.infrastructure.app.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.app.OpenplatformAppQuota;
import com.particle.openplatform.domain.app.OpenplatformAppQuotaId;
import com.particle.openplatform.domain.app.gateway.OpenplatformAppQuotaGateway;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppQuotaDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppQuotaService;
import com.particle.openplatform.infrastructure.app.structmapping.OpenplatformAppQuotaInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台应用额度 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Component
public class OpenplatformAppQuotaGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformAppQuotaId,OpenplatformAppQuota> implements OpenplatformAppQuotaGateway {

    private IOpenplatformAppQuotaService iOpenplatformAppQuotaService;

    @Override
    public OpenplatformAppQuota getById(OpenplatformAppQuotaId openplatformAppQuotaId) {
        OpenplatformAppQuotaDO byId = iOpenplatformAppQuotaService.getById(openplatformAppQuotaId.getId());
        OpenplatformAppQuota openplatformAppQuota = DomainFactory.create(OpenplatformAppQuota.class);
        openplatformAppQuota = OpenplatformAppQuotaInfrastructureStructMapping.instance. openplatformAppQuotaDOToOpenplatformAppQuota(openplatformAppQuota,byId);
        return openplatformAppQuota;
    }

    @Override
    public boolean doSave(OpenplatformAppQuota openplatformAppQuota) {
        OpenplatformAppQuotaDO openplatformAppQuotaDO = OpenplatformAppQuotaInfrastructureStructMapping.instance.openplatformAppQuotaToOpenplatformAppQuotaDO(openplatformAppQuota);
        if (openplatformAppQuotaDO.getId() == null) {
            openplatformAppQuotaDO.setAddControl(openplatformAppQuota.getAddControl());
            OpenplatformAppQuotaDO add = iOpenplatformAppQuotaService.add(openplatformAppQuotaDO);
            openplatformAppQuota.setId(OpenplatformAppQuotaId.of(add.getId()));
            return add != null;
        }
        openplatformAppQuotaDO.setUpdateControl(openplatformAppQuota.getUpdateControl());
        OpenplatformAppQuotaDO update = iOpenplatformAppQuotaService.update(openplatformAppQuotaDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformAppQuotaId openplatformAppQuotaId) {
        return iOpenplatformAppQuotaService.deleteById(openplatformAppQuotaId.getId());
    }

    @Override
    public boolean delete(OpenplatformAppQuotaId openplatformAppQuotaId, IdCommand idCommand) {
        return iOpenplatformAppQuotaService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformAppQuotaService(IOpenplatformAppQuotaService iOpenplatformAppQuotaService) {
        this.iOpenplatformAppQuotaService = iOpenplatformAppQuotaService;
    }
}

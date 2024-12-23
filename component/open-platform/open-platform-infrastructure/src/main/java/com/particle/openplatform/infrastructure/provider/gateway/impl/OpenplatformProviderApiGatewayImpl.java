package com.particle.openplatform.infrastructure.provider.gateway.impl;

import com.particle.common.infrastructure.gateway.AbstractBaseGatewayImpl;
import com.particle.global.domain.DomainFactory;
import com.particle.global.dto.basic.IdCommand;
import com.particle.openplatform.domain.provider.OpenplatformProviderApi;
import com.particle.openplatform.domain.provider.OpenplatformProviderApiId;
import com.particle.openplatform.domain.provider.gateway.OpenplatformProviderApiGateway;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderApiService;
import com.particle.openplatform.infrastructure.provider.structmapping.OpenplatformProviderApiInfrastructureStructMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 开放平台供应商接口 防腐层网关实现
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Component
public class OpenplatformProviderApiGatewayImpl extends AbstractBaseGatewayImpl<OpenplatformProviderApiId,OpenplatformProviderApi> implements OpenplatformProviderApiGateway {

    private IOpenplatformProviderApiService iOpenplatformProviderApiService;

    @Override
    public OpenplatformProviderApi getById(OpenplatformProviderApiId openplatformProviderApiId) {
        OpenplatformProviderApiDO byId = iOpenplatformProviderApiService.getById(openplatformProviderApiId.getId());
        OpenplatformProviderApi openplatformProviderApi = DomainFactory.create(OpenplatformProviderApi.class);
        openplatformProviderApi = OpenplatformProviderApiInfrastructureStructMapping.instance. openplatformProviderApiDOToOpenplatformProviderApi(openplatformProviderApi,byId);
        return openplatformProviderApi;
    }

    @Override
    public boolean doSave(OpenplatformProviderApi openplatformProviderApi) {
        OpenplatformProviderApiDO openplatformProviderApiDO = OpenplatformProviderApiInfrastructureStructMapping.instance.openplatformProviderApiToOpenplatformProviderApiDO(openplatformProviderApi);
        if (openplatformProviderApiDO.getId() == null) {
            openplatformProviderApiDO.setAddControl(openplatformProviderApi.getAddControl());
            OpenplatformProviderApiDO add = iOpenplatformProviderApiService.add(openplatformProviderApiDO);
            openplatformProviderApi.setId(OpenplatformProviderApiId.of(add.getId()));
            return add != null;
        }
        openplatformProviderApiDO.setUpdateControl(openplatformProviderApi.getUpdateControl());
        OpenplatformProviderApiDO update = iOpenplatformProviderApiService.update(openplatformProviderApiDO);
        return update != null;
    }

    @Override
    public boolean delete(OpenplatformProviderApiId openplatformProviderApiId) {
        return iOpenplatformProviderApiService.deleteById(openplatformProviderApiId.getId());
    }

    @Override
    public boolean delete(OpenplatformProviderApiId openplatformProviderApiId, IdCommand idCommand) {
        return iOpenplatformProviderApiService.deleteById(idCommand);
    }

    @Autowired
    public void setIOpenplatformProviderApiService(IOpenplatformProviderApiService iOpenplatformProviderApiService) {
        this.iOpenplatformProviderApiService = iOpenplatformProviderApiService;
    }
}

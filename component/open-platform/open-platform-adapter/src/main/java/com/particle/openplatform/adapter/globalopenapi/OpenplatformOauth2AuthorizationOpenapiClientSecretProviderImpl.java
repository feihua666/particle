package com.particle.openplatform.adapter.globalopenapi;

import com.particle.openplatform.domain.gateway.OpenplatformOauth2RegisteredClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 基于 oauth2authorization 调用实现
 * </p>
 *
 * @author yangwei
 * @since 2025/12/26 09:51
 */
@Component
public class OpenplatformOauth2AuthorizationOpenapiClientSecretProviderImpl implements OpenplatformOpenapiClientSecretProvider{


    @Autowired
    private OpenplatformOauth2RegisteredClientGateway openplatformOauth2RegisteredClientGateway;

    @Override
    public String getClientSecretByClientId(String clientId) {
        return openplatformOauth2RegisteredClientGateway.getSecretByAppId(clientId);
    }
}

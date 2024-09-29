package com.particle.openplatform.infrastructure.gateway.impl;

import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.adapter.feign.client.client.rpc.Oauth2RegisteredClientRpcFeignClient;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.openplatform.domain.gateway.OpenplatformOauth2RegisteredClientGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * oauth2 客户端依赖
 * </p>
 *
 * @author yangwei
 * @since 2024/9/14 16:50
 */
@Component
public class OpenplatformOauth2RegisteredClientGatewayImpl implements OpenplatformOauth2RegisteredClientGateway {
    
    private Oauth2RegisteredClientRpcFeignClient oauth2RegisteredClientRpcFeignClient;

    @Override
    public String getSecretByAppId(String appId) {

        SingleResponse<Oauth2RegisteredClientVO> oauth2RegisteredClientVOSingleResponse = oauth2RegisteredClientRpcFeignClient.getByAppId(appId);
        if (oauth2RegisteredClientVOSingleResponse != null) {
            Oauth2RegisteredClientVO data = oauth2RegisteredClientVOSingleResponse.getData();
            if (data != null) {
                return data.getClientSecret();
            }
        }
        return null;
    }

    @Autowired
    public void setOauth2RegisteredClientRpcFeignClient(Oauth2RegisteredClientRpcFeignClient oauth2RegisteredClientRpcFeignClient) {
        this.oauth2RegisteredClientRpcFeignClient = oauth2RegisteredClientRpcFeignClient;
    }
}

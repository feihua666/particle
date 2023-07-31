package com.particle.oauth2authorization.domain.client.gateway;

import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClient;
import com.particle.oauth2authorization.domain.client.Oauth2RegisteredClientId;
import com.particle.common.domain.gateway.IBaseGateway;

/**
 * <p>
 * oauth2客户端 防腐层
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
public interface Oauth2RegisteredClientGateway extends IBaseGateway<Oauth2RegisteredClientId,Oauth2RegisteredClient> {
}

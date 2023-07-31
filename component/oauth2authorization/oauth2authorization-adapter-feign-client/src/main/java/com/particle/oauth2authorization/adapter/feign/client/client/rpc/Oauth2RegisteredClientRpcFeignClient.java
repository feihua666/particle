package com.particle.oauth2authorization.adapter.feign.client.client.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * oauth2客户端远程调用
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@FeignClient(name = "${particle.feign-client.name.oauth2authorization:oauth2authorization}",path = "/rpc/oauth2_registered_client")
public interface Oauth2RegisteredClientRpcFeignClient {









}

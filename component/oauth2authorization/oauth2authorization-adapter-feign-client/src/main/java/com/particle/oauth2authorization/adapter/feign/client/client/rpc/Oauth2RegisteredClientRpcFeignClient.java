package com.particle.oauth2authorization.adapter.feign.client.client.rpc;

import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

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


    @GetMapping("/getByAppId")
    public SingleResponse<Oauth2RegisteredClientVO> getByAppId(String appId);






}

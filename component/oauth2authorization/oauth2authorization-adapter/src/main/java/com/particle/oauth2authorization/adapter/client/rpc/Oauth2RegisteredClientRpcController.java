package com.particle.oauth2authorization.adapter.client.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.oauth2authorization.client.client.api.IOauth2RegisteredClientApplicationService;
import com.particle.oauth2authorization.adapter.feign.client.client.rpc.Oauth2RegisteredClientRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * oauth2客户端远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-07-25 17:03:38
 */
@Tag(name = "oauth2客户端远程调用相关接口")
@RestController
@RequestMapping("/rpc/oauth2_registered_client")
public class Oauth2RegisteredClientRpcController extends AbstractBaseRpcAdapter implements Oauth2RegisteredClientRpcFeignClient  {

	@Autowired
	private IOauth2RegisteredClientApplicationService iOauth2RegisteredClientApplicationService;


}
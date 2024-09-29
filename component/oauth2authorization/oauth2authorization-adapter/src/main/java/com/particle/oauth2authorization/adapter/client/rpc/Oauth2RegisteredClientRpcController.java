package com.particle.oauth2authorization.adapter.client.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.global.dto.response.SingleResponse;
import com.particle.oauth2authorization.app.client.structmapping.Oauth2RegisteredClientAppStructMapping;
import com.particle.oauth2authorization.client.client.api.IOauth2RegisteredClientApplicationService;
import com.particle.oauth2authorization.adapter.feign.client.client.rpc.Oauth2RegisteredClientRpcFeignClient;
import com.particle.oauth2authorization.client.client.dto.data.Oauth2RegisteredClientVO;
import com.particle.oauth2authorization.infrastructure.client.dos.Oauth2RegisteredClientDO;
import com.particle.oauth2authorization.infrastructure.client.service.IOauth2RegisteredClientService;
import io.swagger.v3.oas.annotations.Operation;
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

	@Autowired
	private IOauth2RegisteredClientService iOauth2RegisteredClientService;

	@Operation(summary = "根据appId获取")
	@Override
	public SingleResponse<Oauth2RegisteredClientVO> getByAppId(String appId) {
		Oauth2RegisteredClientDO byClientId = iOauth2RegisteredClientService.getByClientId(appId);
		return SingleResponse.of(Oauth2RegisteredClientAppStructMapping.instance.oauth2RegisteredClientDOToOauth2RegisteredClientVO(byClientId));
	}
}
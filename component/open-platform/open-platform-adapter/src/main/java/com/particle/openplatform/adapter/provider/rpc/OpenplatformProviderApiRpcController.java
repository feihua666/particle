package com.particle.openplatform.adapter.provider.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApiApplicationService;
import com.particle.openplatform.adapter.feign.client.provider.rpc.OpenplatformProviderApiRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台供应商接口远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Tag(name = "开放平台供应商接口远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_provider_api")
public class OpenplatformProviderApiRpcController extends AbstractBaseRpcAdapter implements OpenplatformProviderApiRpcFeignClient  {

	@Autowired
	private IOpenplatformProviderApiApplicationService iOpenplatformProviderApiApplicationService;


}
package com.particle.openplatform.adapter.provider.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.provider.api.IOpenplatformProviderApplicationService;
import com.particle.openplatform.adapter.feign.client.provider.rpc.OpenplatformProviderRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口供应商远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:58
 */
@Tag(name = "开放平台开放接口供应商远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_provider")
public class OpenplatformProviderRpcController extends AbstractBaseRpcAdapter implements OpenplatformProviderRpcFeignClient  {

	@Autowired
	private IOpenplatformProviderApplicationService iOpenplatformProviderApplicationService;


}
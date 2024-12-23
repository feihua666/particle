package com.particle.openplatform.adapter.openapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.adapter.feign.client.openapi.rpc.OpenplatformOpenapiRpcFeignClient;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Tag(name = "开放平台开放接口远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi")
public class OpenplatformOpenapiRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiApplicationService iOpenplatformOpenapiApplicationService;


}

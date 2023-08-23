package com.particle.openplatform.adapter.openapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiFeeApplicationService;
import com.particle.openplatform.adapter.feign.client.openapi.rpc.OpenplatformOpenapiFeeRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口费用远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Tag(name = "开放平台开放接口费用远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_fee")
public class OpenplatformOpenapiFeeRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiFeeRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiFeeApplicationService iOpenplatformOpenapiFeeApplicationService;


}
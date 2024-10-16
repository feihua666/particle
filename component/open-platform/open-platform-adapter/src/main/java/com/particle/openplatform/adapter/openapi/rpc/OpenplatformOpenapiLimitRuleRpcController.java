package com.particle.openplatform.adapter.openapi.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiLimitRuleApplicationService;
import com.particle.openplatform.adapter.feign.client.openapi.rpc.OpenplatformOpenapiLimitRuleRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口限制规则远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Tag(name = "开放平台开放接口限制规则远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_limit_rule")
public class OpenplatformOpenapiLimitRuleRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiLimitRuleRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiLimitRuleApplicationService iOpenplatformOpenapiLimitRuleApplicationService;


}
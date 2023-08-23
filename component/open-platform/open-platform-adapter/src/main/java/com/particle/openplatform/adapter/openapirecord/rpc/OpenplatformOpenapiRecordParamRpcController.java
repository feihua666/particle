package com.particle.openplatform.adapter.openapirecord.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.openapirecord.api.IOpenplatformOpenapiRecordParamApplicationService;
import com.particle.openplatform.adapter.feign.client.openapirecord.rpc.OpenplatformOpenapiRecordParamRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口调用记录参数远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:15:03
 */
@Tag(name = "开放平台开放接口调用记录参数远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_openapi_record_param")
public class OpenplatformOpenapiRecordParamRpcController extends AbstractBaseRpcAdapter implements OpenplatformOpenapiRecordParamRpcFeignClient  {

	@Autowired
	private IOpenplatformOpenapiRecordParamApplicationService iOpenplatformOpenapiRecordParamApplicationService;


}
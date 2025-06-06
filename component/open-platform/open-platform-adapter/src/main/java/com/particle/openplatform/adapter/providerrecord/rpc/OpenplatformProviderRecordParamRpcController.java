package com.particle.openplatform.adapter.providerrecord.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.adapter.feign.client.providerrecord.rpc.OpenplatformProviderRecordParamRpcFeignClient;
import com.particle.openplatform.client.providerrecord.api.IOpenplatformProviderRecordParamApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口供应商调用记录参数远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-16 16:18:54
 */
@Tag(name = "开放平台开放接口供应商调用记录参数远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_provider_record_param")
public class OpenplatformProviderRecordParamRpcController extends AbstractBaseRpcAdapter implements OpenplatformProviderRecordParamRpcFeignClient  {

	@Autowired
	private IOpenplatformProviderRecordParamApplicationService iOpenplatformProviderRecordParamApplicationService;


}

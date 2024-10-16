package com.particle.openplatform.adapter.app.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppQuotaApplicationService;
import com.particle.openplatform.adapter.feign.client.app.rpc.OpenplatformAppQuotaRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用额度远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2024-10-16 10:38:41
 */
@Tag(name = "开放平台应用额度远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_app_quota")
public class OpenplatformAppQuotaRpcController extends AbstractBaseRpcAdapter implements OpenplatformAppQuotaRpcFeignClient  {

	@Autowired
	private IOpenplatformAppQuotaApplicationService iOpenplatformAppQuotaApplicationService;


}
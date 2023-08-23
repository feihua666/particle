package com.particle.openplatform.adapter.app.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.openplatform.client.app.api.IOpenplatformAppApplicationService;
import com.particle.openplatform.adapter.feign.client.app.rpc.OpenplatformAppRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台应用远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Tag(name = "开放平台应用远程调用相关接口")
@RestController
@RequestMapping("/rpc/openplatform_app")
public class OpenplatformAppRpcController extends AbstractBaseRpcAdapter implements OpenplatformAppRpcFeignClient  {

	@Autowired
	private IOpenplatformAppApplicationService iOpenplatformAppApplicationService;


}
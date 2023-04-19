package com.particle.tenant.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tenant.client.api.ITenantApplicationService;
import com.particle.tenant.adapter.feign.client.rpc.TenantRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Api(tags = "租户远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant")
public class TenantRpcController extends AbstractBaseRpcAdapter implements TenantRpcFeignClient  {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;


}
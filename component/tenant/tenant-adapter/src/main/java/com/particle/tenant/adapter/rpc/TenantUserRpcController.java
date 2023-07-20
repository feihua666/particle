package com.particle.tenant.adapter.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import com.particle.tenant.adapter.feign.client.rpc.TenantUserRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Tag(name = "租户用户远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_user")
public class TenantUserRpcController extends AbstractBaseRpcAdapter implements TenantUserRpcFeignClient  {

	@Autowired
	private ITenantUserApplicationService iTenantUserApplicationService;


}
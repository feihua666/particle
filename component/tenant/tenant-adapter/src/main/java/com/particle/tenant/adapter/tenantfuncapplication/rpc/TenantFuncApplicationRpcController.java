package com.particle.tenant.adapter.tenantfuncapplication.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tenant.client.tenantfuncapplication.api.ITenantFuncApplicationApplicationService;
import com.particle.tenant.adapter.feign.client.tenantfuncapplication.rpc.TenantFuncApplicationRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能应用远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Tag(name = "租户功能应用远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_func_application")
public class TenantFuncApplicationRpcController extends AbstractBaseRpcAdapter implements TenantFuncApplicationRpcFeignClient  {

	@Autowired
	private ITenantFuncApplicationApplicationService iTenantFuncApplicationApplicationService;


}
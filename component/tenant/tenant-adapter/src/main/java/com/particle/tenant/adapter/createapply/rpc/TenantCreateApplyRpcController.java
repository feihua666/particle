package com.particle.tenant.adapter.createapply.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tenant.client.createapply.api.ITenantCreateApplyApplicationService;
import com.particle.tenant.adapter.feign.client.createapply.rpc.TenantCreateApplyRpcFeignClient;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户创建申请远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Tag(name = "租户创建申请远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_create_apply")
public class TenantCreateApplyRpcController extends AbstractBaseRpcAdapter implements TenantCreateApplyRpcFeignClient  {

	@Autowired
	private ITenantCreateApplyApplicationService iTenantCreateApplyApplicationService;


}
package com.particle.tenant.adapter.userinvite.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tenant.adapter.feign.client.userinvite.rpc.TenantUserInviteRpcFeignClient;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Tag(name = "租户用户邀请远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_user_invite")
public class TenantUserInviteRpcController extends AbstractBaseRpcAdapter implements TenantUserInviteRpcFeignClient  {

	@Autowired
	private ITenantUserInviteApplicationService iTenantUserInviteApplicationService;


}

package com.particle.tenant.adapter.userinvite.rpc;

import com.particle.common.adapter.rpc.AbstractBaseRpcAdapter;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteUserRecordApplicationService;
import com.particle.tenant.adapter.feign.client.userinvite.rpc.TenantUserInviteUserRecordRpcFeignClient;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请记录远程调用适配器
 * 主要用于OpenFeignClient远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Api(tags = "租户用户邀请记录远程调用相关接口")
@RestController
@RequestMapping("/rpc/tenant_user_invite_user_record")
public class TenantUserInviteUserRecordRpcController extends AbstractBaseRpcAdapter implements TenantUserInviteUserRecordRpcFeignClient  {

	@Autowired
	private ITenantUserInviteUserRecordApplicationService iTenantUserInviteUserRecordApplicationService;


}
package com.particle.tenant.adapter.feign.client.userinvite.rpc;

import org.springframework.cloud.openfeign.FeignClient;
/**
 * <p>
 * 租户用户邀请记录远程调用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@FeignClient(name = "${particle.feign-client.name.tenant:tenant}",path = "/rpc/tenant_user_invite_user_record")
public interface TenantUserInviteUserRecordRpcFeignClient {









}

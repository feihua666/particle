package com.particle.tenant.adapter.userinvite.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.tenant.client.userinvite.api.ITenantUserInviteApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Tag(name = "租户用户邀请移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/tenant_user_invite")
public class TenantUserInviteAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITenantUserInviteApplicationService iTenantUserInviteApplicationService;


}
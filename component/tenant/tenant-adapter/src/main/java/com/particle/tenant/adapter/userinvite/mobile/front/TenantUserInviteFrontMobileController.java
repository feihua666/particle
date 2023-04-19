package com.particle.tenant.adapter.userinvite.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Api(tags = "租户用户邀请移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/tenant_user_invite")
public class TenantUserInviteFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITenantUserInviteApplicationService iTenantUserInviteApplicationService;


}
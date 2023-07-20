package com.particle.tenant.adapter.userinvite.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:04:07
 */
@Tag(name = "租户用户邀请wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/tenant_user_invite")
public class TenantUserInviteFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantUserInviteApplicationService iTenantUserInviteApplicationService;


}
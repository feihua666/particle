package com.particle.tenant.adapter.userinvite.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteUserRecordApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请记录前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Api(tags = "租户用户邀请记录pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tenant_user_invite_user_record")
public class TenantUserInviteUserRecordFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantUserInviteUserRecordApplicationService iTenantUserInviteUserRecordApplicationService;


}
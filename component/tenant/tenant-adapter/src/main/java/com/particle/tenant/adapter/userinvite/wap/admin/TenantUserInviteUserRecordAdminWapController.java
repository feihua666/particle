package com.particle.tenant.adapter.userinvite.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.userinvite.api.ITenantUserInviteUserRecordApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户邀请记录后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:06:17
 */
@Tag(name = "租户用户邀请记录wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tenant_user_invite_user_record")
public class TenantUserInviteUserRecordAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantUserInviteUserRecordApplicationService iTenantUserInviteUserRecordApplicationService;


}
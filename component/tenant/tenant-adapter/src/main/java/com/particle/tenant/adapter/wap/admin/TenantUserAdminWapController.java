package com.particle.tenant.adapter.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Api(tags = "租户用户wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tenant_user")
public class TenantUserAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantUserApplicationService iTenantUserApplicationService;


}
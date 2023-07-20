package com.particle.tenant.adapter.createapply.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.createapply.api.ITenantCreateApplyApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户创建申请后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Tag(name = "租户创建申请wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tenant_create_apply")
public class TenantCreateApplyAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantCreateApplyApplicationService iTenantCreateApplyApplicationService;


}
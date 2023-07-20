package com.particle.tenant.adapter.tenantfunc.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.tenantfunc.api.ITenantFuncApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能菜单后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Tag(name = "租户功能菜单wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/tenant_func")
public class TenantFuncAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantFuncApplicationService iTenantFuncApplicationService;


}
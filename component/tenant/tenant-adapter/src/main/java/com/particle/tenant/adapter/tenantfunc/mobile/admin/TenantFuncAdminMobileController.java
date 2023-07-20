package com.particle.tenant.adapter.tenantfunc.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.tenant.client.tenantfunc.api.ITenantFuncApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能菜单后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Tag(name = "租户功能菜单移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/tenant_func")
public class TenantFuncAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITenantFuncApplicationService iTenantFuncApplicationService;


}
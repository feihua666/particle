package com.particle.tenant.adapter.tenantfuncapplication.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.tenant.client.tenantfuncapplication.api.ITenantFuncApplicationApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能应用后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Api(tags = "租户功能应用移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/tenant_func_application")
public class TenantFuncApplicationAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITenantFuncApplicationApplicationService iTenantFuncApplicationApplicationService;


}
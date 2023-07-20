package com.particle.tenant.adapter.tenantfunc.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.tenant.client.tenantfunc.api.ITenantFuncApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能菜单前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:11:17
 */
@Tag(name = "租户功能菜单移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/tenant_func")
public class TenantFuncFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITenantFuncApplicationService iTenantFuncApplicationService;


}
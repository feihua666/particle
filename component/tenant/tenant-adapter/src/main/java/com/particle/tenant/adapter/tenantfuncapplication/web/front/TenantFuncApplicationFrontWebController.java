package com.particle.tenant.adapter.tenantfuncapplication.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.tenant.client.tenantfuncapplication.api.ITenantFuncApplicationApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能应用前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Tag(name = "租户功能应用pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tenant_func_application")
public class TenantFuncApplicationFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantFuncApplicationApplicationService iTenantFuncApplicationApplicationService;


}
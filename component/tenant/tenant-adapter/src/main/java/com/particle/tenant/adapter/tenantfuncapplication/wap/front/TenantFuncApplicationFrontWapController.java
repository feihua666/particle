package com.particle.tenant.adapter.tenantfuncapplication.wap.front;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.tenant.client.tenantfuncapplication.api.ITenantFuncApplicationApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户功能应用前台应用wap端前端适配器
 * 主要用于wap端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-17 18:12:06
 */
@Api(tags = "租户功能应用wap端前台应用相关接口")
@RestController
@RequestMapping("/front/wap/tenant_func_application")
public class TenantFuncApplicationFrontWapController extends AbstractBaseWapAdapter {

	@Autowired
	private ITenantFuncApplicationApplicationService iTenantFuncApplicationApplicationService;


}
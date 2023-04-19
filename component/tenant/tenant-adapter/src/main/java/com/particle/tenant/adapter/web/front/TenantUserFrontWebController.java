package com.particle.tenant.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.tenant.client.api.ITenantUserApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户用户前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-12 15:36:44
 */
@Api(tags = "租户用户pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tenant_user")
public class TenantUserFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantUserApplicationService iTenantUserApplicationService;


}
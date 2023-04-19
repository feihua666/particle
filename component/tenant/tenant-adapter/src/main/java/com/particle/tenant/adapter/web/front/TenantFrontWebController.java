package com.particle.tenant.adapter.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.tenant.client.api.ITenantApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-11 22:25:27
 */
@Api(tags = "租户pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tenant")
public class TenantFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantApplicationService iTenantApplicationService;


}
package com.particle.tenant.adapter.createapply.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.tenant.client.createapply.api.ITenantCreateApplyApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户创建申请前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Api(tags = "租户创建申请pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/tenant_create_apply")
public class TenantCreateApplyFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private ITenantCreateApplyApplicationService iTenantCreateApplyApplicationService;


}
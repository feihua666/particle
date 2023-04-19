package com.particle.tenant.adapter.createapply.mobile.front;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import com.particle.tenant.client.createapply.api.ITenantCreateApplyApplicationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 租户创建申请前台应用移动端前端适配器
 * 主要用于移动端前台应用
 * </p>
 *
 * @author yw
 * @since 2023-04-18 11:01:30
 */
@Api(tags = "租户创建申请移动端前台应用相关接口")
@RestController
@RequestMapping("/front/mobile/tenant_create_apply")
public class TenantCreateApplyFrontMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private ITenantCreateApplyApplicationService iTenantCreateApplyApplicationService;


}
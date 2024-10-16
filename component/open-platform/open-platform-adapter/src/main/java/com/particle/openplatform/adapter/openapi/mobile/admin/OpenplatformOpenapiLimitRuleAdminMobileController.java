package com.particle.openplatform.adapter.openapi.mobile.admin;

import com.particle.common.adapter.mobile.AbstractBaseMobileAdapter;
import  com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiLimitRuleApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口限制规则后台管理移动端前端适配器
 * 主要用于移动端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Tag(name = "开放平台开放接口限制规则移动端后台管理相关接口")
@RestController
@RequestMapping("/admin/mobile/openplatform_openapi_limit_rule")
public class OpenplatformOpenapiLimitRuleAdminMobileController extends AbstractBaseMobileAdapter {

	@Autowired
	private IOpenplatformOpenapiLimitRuleApplicationService iOpenplatformOpenapiLimitRuleApplicationService;


}
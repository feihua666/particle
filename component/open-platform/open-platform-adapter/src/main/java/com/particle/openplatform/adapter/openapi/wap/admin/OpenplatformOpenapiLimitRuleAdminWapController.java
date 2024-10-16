package com.particle.openplatform.adapter.openapi.wap.admin;

import com.particle.common.adapter.wap.AbstractBaseWapAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiLimitRuleApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口限制规则后台管理wap端前端适配器
 * 主要用于wap端后台管理
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Tag(name = "开放平台开放接口限制规则wap端后台管理相关接口")
@RestController
@RequestMapping("/admin/wap/openplatform_openapi_limit_rule")
public class OpenplatformOpenapiLimitRuleAdminWapController extends AbstractBaseWapAdapter {

	@Autowired
	private IOpenplatformOpenapiLimitRuleApplicationService iOpenplatformOpenapiLimitRuleApplicationService;


}
package com.particle.openplatform.adapter.openapi.web.front;

import com.particle.common.adapter.web.AbstractBaseWebAdapter;
import com.particle.openplatform.client.openapi.api.IOpenplatformOpenapiLimitRuleApplicationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 开放平台开放接口限制规则前台应用pc或平板端前端适配器
 * 主要用于pc或平板端前台应用
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Tag(name = "开放平台开放接口限制规则pc或平板端前台应用相关接口")
@RestController
@RequestMapping("/front/web/openplatform_openapi_limit_rule")
public class OpenplatformOpenapiLimitRuleFrontWebController extends AbstractBaseWebAdapter {

	@Autowired
	private IOpenplatformOpenapiLimitRuleApplicationService iOpenplatformOpenapiLimitRuleApplicationService;


}
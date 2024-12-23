package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiLimitRuleGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiLimitRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台开放接口限制规则 指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Component
@Validated
public class OpenplatformOpenapiLimitRuleCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiLimitRuleGateway openplatformOpenapiLimitRuleGateway;
	private IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService;
	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiLimitRuleGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiLimitRuleGateway(OpenplatformOpenapiLimitRuleGateway openplatformOpenapiLimitRuleGateway) {
		this.openplatformOpenapiLimitRuleGateway = openplatformOpenapiLimitRuleGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiLimitRuleService(IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService) {
		this.iOpenplatformOpenapiLimitRuleService = iOpenplatformOpenapiLimitRuleService;
	}
}

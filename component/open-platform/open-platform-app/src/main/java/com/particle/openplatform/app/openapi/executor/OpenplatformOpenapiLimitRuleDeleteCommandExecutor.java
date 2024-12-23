package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiLimitRuleAppStructMapping;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRule;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRuleId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiLimitRuleGateway;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiLimitRuleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台开放接口限制规则 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-14 11:03:30
 */
@Component
@Validated
public class OpenplatformOpenapiLimitRuleDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiLimitRuleGateway openplatformOpenapiLimitRuleGateway;
	private IOpenplatformOpenapiLimitRuleService iOpenplatformOpenapiLimitRuleService;

	/**
	 * 执行 开放平台开放接口限制规则 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiLimitRuleVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiLimitRuleId openplatformOpenapiLimitRuleId = OpenplatformOpenapiLimitRuleId.of(deleteCommand.getId());
		OpenplatformOpenapiLimitRule byId = openplatformOpenapiLimitRuleGateway.getById(openplatformOpenapiLimitRuleId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiLimitRuleGateway.delete(openplatformOpenapiLimitRuleId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiLimitRuleAppStructMapping.instance.toOpenplatformOpenapiLimitRuleVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


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

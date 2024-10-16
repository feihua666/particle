package com.particle.openplatform.app.openapi.executor;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiLimitRuleAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRule;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiLimitRuleGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

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
public class OpenplatformOpenapiLimitRuleCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiLimitRuleGateway openplatformOpenapiLimitRuleGateway;

	/**
	 * 执行开放平台开放接口限制规则添加指令
	 * @param openplatformOpenapiLimitRuleCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiLimitRuleVO> execute(@Valid OpenplatformOpenapiLimitRuleCreateCommand openplatformOpenapiLimitRuleCreateCommand) {
		OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule = createByOpenplatformOpenapiLimitRuleCreateCommand(openplatformOpenapiLimitRuleCreateCommand);
		openplatformOpenapiLimitRule.setAddControl(openplatformOpenapiLimitRuleCreateCommand);
		boolean save = openplatformOpenapiLimitRuleGateway.save(openplatformOpenapiLimitRule);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiLimitRuleAppStructMapping.instance.toOpenplatformOpenapiLimitRuleVO(openplatformOpenapiLimitRule));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口限制规则创建指令创建开放平台开放接口限制规则模型
	 * @param openplatformOpenapiLimitRuleCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiLimitRule createByOpenplatformOpenapiLimitRuleCreateCommand(OpenplatformOpenapiLimitRuleCreateCommand openplatformOpenapiLimitRuleCreateCommand){
		OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule = OpenplatformOpenapiLimitRule.create();
		OpenplatformOpenapiLimitRuleCreateCommandToOpenplatformOpenapiLimitRuleMapping.instance.fillOpenplatformOpenapiLimitRuleByOpenplatformOpenapiLimitRuleCreateCommand(openplatformOpenapiLimitRule, openplatformOpenapiLimitRuleCreateCommand);
		return openplatformOpenapiLimitRule;
	}

	@Mapper
	interface  OpenplatformOpenapiLimitRuleCreateCommandToOpenplatformOpenapiLimitRuleMapping{
		OpenplatformOpenapiLimitRuleCreateCommandToOpenplatformOpenapiLimitRuleMapping instance = Mappers.getMapper( OpenplatformOpenapiLimitRuleCreateCommandToOpenplatformOpenapiLimitRuleMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiLimitRule
		 * @param openplatformOpenapiLimitRuleCreateCommand
		 */
		void fillOpenplatformOpenapiLimitRuleByOpenplatformOpenapiLimitRuleCreateCommand(@MappingTarget OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule, OpenplatformOpenapiLimitRuleCreateCommand openplatformOpenapiLimitRuleCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiLimitRuleGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiLimitRuleGateway(OpenplatformOpenapiLimitRuleGateway openplatformOpenapiLimitRuleGateway) {
		this.openplatformOpenapiLimitRuleGateway = openplatformOpenapiLimitRuleGateway;
	}
}

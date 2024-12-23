package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiLimitRuleAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiLimitRuleUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiLimitRuleVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRule;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiLimitRuleId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiLimitRuleGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台开放接口限制规则 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiLimitRuleUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiLimitRuleGateway openplatformOpenapiLimitRuleGateway;

	/**
	 * 执行 开放平台开放接口限制规则 更新指令
	 * @param openplatformOpenapiLimitRuleUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiLimitRuleVO> execute(@Valid OpenplatformOpenapiLimitRuleUpdateCommand openplatformOpenapiLimitRuleUpdateCommand) {
		OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule = createByOpenplatformOpenapiLimitRuleUpdateCommand(openplatformOpenapiLimitRuleUpdateCommand);
		openplatformOpenapiLimitRule.setUpdateControl(openplatformOpenapiLimitRuleUpdateCommand);
		boolean save = openplatformOpenapiLimitRuleGateway.save(openplatformOpenapiLimitRule);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiLimitRuleAppStructMapping.instance.toOpenplatformOpenapiLimitRuleVO(openplatformOpenapiLimitRule));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台开放接口限制规则更新指令创建开放平台开放接口限制规则模型
	 * @param openplatformOpenapiLimitRuleUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiLimitRule createByOpenplatformOpenapiLimitRuleUpdateCommand(OpenplatformOpenapiLimitRuleUpdateCommand openplatformOpenapiLimitRuleUpdateCommand){
		OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule = OpenplatformOpenapiLimitRule.create();
		OpenplatformOpenapiLimitRuleUpdateCommandToOpenplatformOpenapiLimitRuleMapping.instance.fillOpenplatformOpenapiLimitRuleByOpenplatformOpenapiLimitRuleUpdateCommand(openplatformOpenapiLimitRule, openplatformOpenapiLimitRuleUpdateCommand);
		return openplatformOpenapiLimitRule;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformOpenapiLimitRuleUpdateCommandToOpenplatformOpenapiLimitRuleMapping{
		OpenplatformOpenapiLimitRuleUpdateCommandToOpenplatformOpenapiLimitRuleMapping instance = Mappers.getMapper(OpenplatformOpenapiLimitRuleUpdateCommandToOpenplatformOpenapiLimitRuleMapping.class );

		default OpenplatformOpenapiLimitRuleId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiLimitRuleId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiLimitRule
		 * @param openplatformOpenapiLimitRuleUpdateCommand
		 */
		void fillOpenplatformOpenapiLimitRuleByOpenplatformOpenapiLimitRuleUpdateCommand(@MappingTarget OpenplatformOpenapiLimitRule openplatformOpenapiLimitRule, OpenplatformOpenapiLimitRuleUpdateCommand openplatformOpenapiLimitRuleUpdateCommand);
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

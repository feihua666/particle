package com.particle.lowcode.app.generator.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplate;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentTemplateGateway;
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
 * 低代码片段模板 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Component
@Validated
public class LowcodeSegmentTemplateCreateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeSegmentTemplateGateway lowcodeSegmentTemplateGateway;

	/**
	 * 执行低代码片段模板添加指令
	 * @param lowcodeSegmentTemplateCreateCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateVO> execute(@Valid LowcodeSegmentTemplateCreateCommand lowcodeSegmentTemplateCreateCommand) {
		LowcodeSegmentTemplate lowcodeSegmentTemplate = createByLowcodeSegmentTemplateCreateCommand(lowcodeSegmentTemplateCreateCommand);
		lowcodeSegmentTemplate.setAddControl(lowcodeSegmentTemplateCreateCommand);
		boolean save = lowcodeSegmentTemplateGateway.save(lowcodeSegmentTemplate);
		if (save) {
			return SingleResponse.of(LowcodeSegmentTemplateAppStructMapping.instance.toLowcodeSegmentTemplateVO(lowcodeSegmentTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据低代码片段模板创建指令创建低代码片段模板模型
	 * @param lowcodeSegmentTemplateCreateCommand
	 * @return
	 */
	private LowcodeSegmentTemplate createByLowcodeSegmentTemplateCreateCommand(LowcodeSegmentTemplateCreateCommand lowcodeSegmentTemplateCreateCommand){
		LowcodeSegmentTemplate lowcodeSegmentTemplate = LowcodeSegmentTemplate.create();
		LowcodeSegmentTemplateCreateCommandToLowcodeSegmentTemplateMapping.instance.fillLowcodeSegmentTemplateByLowcodeSegmentTemplateCreateCommand(lowcodeSegmentTemplate, lowcodeSegmentTemplateCreateCommand);
		return lowcodeSegmentTemplate;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  LowcodeSegmentTemplateCreateCommandToLowcodeSegmentTemplateMapping{
		LowcodeSegmentTemplateCreateCommandToLowcodeSegmentTemplateMapping instance = Mappers.getMapper( LowcodeSegmentTemplateCreateCommandToLowcodeSegmentTemplateMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeSegmentTemplate
		 * @param lowcodeSegmentTemplateCreateCommand
		 */
		void fillLowcodeSegmentTemplateByLowcodeSegmentTemplateCreateCommand(@MappingTarget LowcodeSegmentTemplate lowcodeSegmentTemplate, LowcodeSegmentTemplateCreateCommand lowcodeSegmentTemplateCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param lowcodeSegmentTemplateGateway
	 */
	@Autowired
	public void setLowcodeSegmentTemplateGateway(LowcodeSegmentTemplateGateway lowcodeSegmentTemplateGateway) {
		this.lowcodeSegmentTemplateGateway = lowcodeSegmentTemplateGateway;
	}
}

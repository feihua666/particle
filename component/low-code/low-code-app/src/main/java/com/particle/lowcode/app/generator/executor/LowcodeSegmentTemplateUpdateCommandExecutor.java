package com.particle.lowcode.app.generator.executor;

import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateUpdateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplate;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplateId;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentTemplateGateway;
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
 * 低代码片段模板 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-06
 */
@Component
@Validated
public class LowcodeSegmentTemplateUpdateCommandExecutor  extends AbstractBaseExecutor {

	private LowcodeSegmentTemplateGateway lowcodeSegmentTemplateGateway;

	/**
	 * 执行 低代码片段模板 更新指令
	 * @param lowcodeSegmentTemplateUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateVO> execute(@Valid LowcodeSegmentTemplateUpdateCommand lowcodeSegmentTemplateUpdateCommand) {
		LowcodeSegmentTemplate lowcodeSegmentTemplate = createByLowcodeSegmentTemplateUpdateCommand(lowcodeSegmentTemplateUpdateCommand);
		lowcodeSegmentTemplate.setUpdateControl(lowcodeSegmentTemplateUpdateCommand);
		boolean save = lowcodeSegmentTemplateGateway.save(lowcodeSegmentTemplate);
		if (save) {
			return SingleResponse.of(LowcodeSegmentTemplateAppStructMapping.instance.toLowcodeSegmentTemplateVO(lowcodeSegmentTemplate));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据区域创建指令创建区域模型
	 * @param lowcodeSegmentTemplateUpdateCommand
	 * @return
	 */
	private LowcodeSegmentTemplate createByLowcodeSegmentTemplateUpdateCommand(LowcodeSegmentTemplateUpdateCommand lowcodeSegmentTemplateUpdateCommand){
		LowcodeSegmentTemplate lowcodeSegmentTemplate = LowcodeSegmentTemplate.create();
		LowcodeSegmentTemplateUpdateCommandToLowcodeSegmentTemplateMapping.instance.fillLowcodeSegmentTemplateByLowcodeSegmentTemplateUpdateCommand(lowcodeSegmentTemplate, lowcodeSegmentTemplateUpdateCommand);
		return lowcodeSegmentTemplate;
	}

	@Mapper
	interface LowcodeSegmentTemplateUpdateCommandToLowcodeSegmentTemplateMapping{
		LowcodeSegmentTemplateUpdateCommandToLowcodeSegmentTemplateMapping instance = Mappers.getMapper(LowcodeSegmentTemplateUpdateCommandToLowcodeSegmentTemplateMapping.class );

		default LowcodeSegmentTemplateId map(Long id){
			if (id == null) {
				return null;
			}
			return LowcodeSegmentTemplateId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param lowcodeSegmentTemplate
		 * @param lowcodeSegmentTemplateUpdateCommand
		 */
		void fillLowcodeSegmentTemplateByLowcodeSegmentTemplateUpdateCommand(@MappingTarget LowcodeSegmentTemplate lowcodeSegmentTemplate, LowcodeSegmentTemplateUpdateCommand lowcodeSegmentTemplateUpdateCommand);
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

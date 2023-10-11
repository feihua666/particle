package com.particle.lowcode.app.generator.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateCopyCommand;
import com.particle.lowcode.client.generator.dto.command.LowcodeSegmentTemplateCreateCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.lowcode.domain.generator.LowcodeSegmentTemplate;
import com.particle.lowcode.domain.generator.gateway.LowcodeSegmentTemplateGateway;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 低代码片段模板 复制指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-02-17
 */
@Component
@Validated
public class LowcodeSegmentTemplateCopyCommandExecutor extends AbstractBaseExecutor {


	private ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService;

	/**
	 * 复制
	 * @param lowcodeSegmentTemplateCopyCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateVO> copy(@Valid LowcodeSegmentTemplateCopyCommand lowcodeSegmentTemplateCopyCommand) {
		LowcodeSegmentTemplateDO copy = iLowcodeSegmentTemplateService.copyAndCopyChildren(lowcodeSegmentTemplateCopyCommand.getId(), (handleDO) -> {

			if (lowcodeSegmentTemplateCopyCommand.getId().equals(handleDO.getId())) {
				handleDO.setParentId(lowcodeSegmentTemplateCopyCommand.getParentId());
			}
			String keyWordReplace = lowcodeSegmentTemplateCopyCommand.getKeyWordReplace();
			if (StrUtil.isNotEmpty(keyWordReplace)) {
				for (String keyWord : keyWordReplace.split(lowcodeSegmentTemplateCopyCommand.getGroupSeperator())) {
					String[] split = keyWord.split(lowcodeSegmentTemplateCopyCommand.getSeperator());
					handleDO.setCode(Optional.ofNullable(handleDO.getCode()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setName(Optional.ofNullable(handleDO.getName()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setNameTemplate(Optional.ofNullable(handleDO.getNameTemplate()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setNameOutputVariable(Optional.ofNullable(handleDO.getNameOutputVariable()).map(i -> i.replace(split[0], split[1])).orElse(null));

					handleDO.setContentTemplate(Optional.ofNullable(handleDO.getContentTemplate()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setOutputVariable(Optional.ofNullable(handleDO.getOutputVariable()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setShareVariables(Optional.ofNullable(handleDO.getShareVariables()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setRemark(Optional.ofNullable(handleDO.getRemark()).map(i -> i.replace(split[0], split[1])).orElse(null));
				}
			}

			return handleDO;
		}, lowcodeSegmentTemplateCopyCommand.getIsIncludeAllChildren());
		if (copy != null) {
			return SingleResponse.of(LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateDOToLowcodeSegmentTemplateVO(copy));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	@Autowired
	public void setiLowcodeSegmentTemplateService(ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService) {
		this.iLowcodeSegmentTemplateService = iLowcodeSegmentTemplateService;
	}
}

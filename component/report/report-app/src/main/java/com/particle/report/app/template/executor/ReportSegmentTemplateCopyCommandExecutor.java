package com.particle.report.app.template.executor;

import cn.hutool.core.util.StrUtil;
import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.report.app.template.structmapping.ReportSegmentTemplateAppStructMapping;
import com.particle.report.client.template.dto.command.ReportSegmentTemplateCopyCommand;
import com.particle.report.client.template.dto.data.ReportSegmentTemplateVO;
import com.particle.report.infrastructure.template.dos.ReportSegmentTemplateDO;
import com.particle.report.infrastructure.template.service.IReportSegmentTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.Optional;

/**
 * <p>
 * 报告片段模板 复制指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-09-13 18:02:51
 */
@Component
@Validated
public class ReportSegmentTemplateCopyCommandExecutor extends AbstractBaseExecutor {


	private IReportSegmentTemplateService iReportSegmentTemplateService;

	/**
	 * 复制
	 * @param reportSegmentTemplateCopyCommand
	 * @return
	 */
	public SingleResponse<ReportSegmentTemplateVO> copy(@Valid ReportSegmentTemplateCopyCommand reportSegmentTemplateCopyCommand) {
		ReportSegmentTemplateDO copy = iReportSegmentTemplateService.copyAndCopyChildren(reportSegmentTemplateCopyCommand.getId(), (handleDO) -> {

			if (reportSegmentTemplateCopyCommand.getId().equals(handleDO.getId())) {
				handleDO.setParentId(reportSegmentTemplateCopyCommand.getParentId());
			}
			String keyWordReplace = reportSegmentTemplateCopyCommand.getKeyWordReplace();
			if (StrUtil.isNotEmpty(keyWordReplace)) {
				for (String keyWord : keyWordReplace.split("===")) {
					String[] split = keyWord.split("=");
					handleDO.setCode(Optional.ofNullable(handleDO.getCode()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setName(Optional.ofNullable(handleDO.getName()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setNameTemplate(Optional.ofNullable(handleDO.getNameTemplate()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setComputeTemplate(Optional.ofNullable(handleDO.getComputeTemplate()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setDataResolveScript(Optional.ofNullable(handleDO.getDataResolveScript()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setNameOutputVariable(Optional.ofNullable(handleDO.getNameOutputVariable()).map(i -> i.replace(split[0], split[1])).orElse(null));

					handleDO.setContentTemplate(Optional.ofNullable(handleDO.getContentTemplate()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setOutputVariable(Optional.ofNullable(handleDO.getOutputVariable()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setShareVariables(Optional.ofNullable(handleDO.getShareVariables()).map(i -> i.replace(split[0], split[1])).orElse(null));
					handleDO.setRemark(Optional.ofNullable(handleDO.getRemark()).map(i -> i.replace(split[0], split[1])).orElse(null));
				}
			}

			return handleDO;
		}, reportSegmentTemplateCopyCommand.getIsIncludeAllChildren());
		if (copy != null) {
			return SingleResponse.of(ReportSegmentTemplateAppStructMapping.instance.reportSegmentTemplateDOToReportSegmentTemplateVO(copy));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	@Autowired
	public void setiReportSegmentTemplateService(IReportSegmentTemplateService iReportSegmentTemplateService) {
		this.iReportSegmentTemplateService = iReportSegmentTemplateService;
	}
}

package com.particle.lowcode.app.generator.executor.representation;

import com.particle.lowcode.app.generator.structmapping.LowcodeSegmentTemplateAppStructMapping;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplateQueryListCommand;
import com.particle.lowcode.client.generator.dto.data.LowcodeSegmentTemplateVO;
import com.particle.lowcode.infrastructure.generator.dos.LowcodeSegmentTemplateDO;
import com.particle.lowcode.infrastructure.generator.service.ILowcodeSegmentTemplateService;
import com.particle.lowcode.client.generator.dto.command.representation.LowcodeSegmentTemplatePageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 低代码片段模板 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-01-06
 */
@Component
@Validated
public class LowcodeSegmentTemplateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService;

	/**
	 * 执行 低代码片段模板 列表查询指令
	 * @param lowcodeSegmentTemplateQueryListCommand
	 * @return
	 */
	public MultiResponse<LowcodeSegmentTemplateVO> execute(@Valid LowcodeSegmentTemplateQueryListCommand lowcodeSegmentTemplateQueryListCommand) {
		List<LowcodeSegmentTemplateDO> lowcodeSegmentTemplateDO = iLowcodeSegmentTemplateService.list(lowcodeSegmentTemplateQueryListCommand);
		List<LowcodeSegmentTemplateVO> lowcodeSegmentTemplateVOs = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateDOsToLowcodeSegmentTemplateVOs(lowcodeSegmentTemplateDO);
		return MultiResponse.of(lowcodeSegmentTemplateVOs);
	}
	/**
	 * 执行 低代码片段模板 分页查询指令
	 * @param lowcodeSegmentTemplatePageQueryCommand
	 * @return
	 */
	public PageResponse<LowcodeSegmentTemplateVO> execute(@Valid LowcodeSegmentTemplatePageQueryCommand lowcodeSegmentTemplatePageQueryCommand) {
		Page<LowcodeSegmentTemplateDO> page = iLowcodeSegmentTemplateService.listPage(lowcodeSegmentTemplatePageQueryCommand);
		return LowcodeSegmentTemplateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 低代码片段模板 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateVO> executeDetail(IdCommand detailCommand) {
		LowcodeSegmentTemplateDO byId = iLowcodeSegmentTemplateService.getById(detailCommand.getId());
		LowcodeSegmentTemplateVO lowcodeSegmentTemplateVO = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateDOToLowcodeSegmentTemplateVO(byId);
		return SingleResponse.of(lowcodeSegmentTemplateVO);
	}
	/**
	 * 执行 低代码片段模板 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<LowcodeSegmentTemplateVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		LowcodeSegmentTemplateDO byId = iLowcodeSegmentTemplateService.getById(detailForUpdateCommand.getId());
		LowcodeSegmentTemplateVO lowcodeSegmentTemplateVO = LowcodeSegmentTemplateAppStructMapping.instance.lowcodeSegmentTemplateDOToLowcodeSegmentTemplateVO(byId);
		return SingleResponse.of(lowcodeSegmentTemplateVO);
	}

	@Autowired
	public void setILowcodeSegmentTemplateService(ILowcodeSegmentTemplateService iLowcodeSegmentTemplateService) {
		this.iLowcodeSegmentTemplateService = iLowcodeSegmentTemplateService;
	}
}

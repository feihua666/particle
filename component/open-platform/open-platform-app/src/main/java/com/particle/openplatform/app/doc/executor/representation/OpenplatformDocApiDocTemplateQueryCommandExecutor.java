package com.particle.openplatform.app.doc.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplatePageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-18 16:48:39
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocTemplateService iOpenplatformDocApiDocTemplateService;

	/**
	 * 执行 开放接口文档模板 列表查询指令
	 * @param openplatformDocApiDocTemplateQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocTemplateVO> execute(@Valid OpenplatformDocApiDocTemplateQueryListCommand openplatformDocApiDocTemplateQueryListCommand) {
		List<OpenplatformDocApiDocTemplateDO> openplatformDocApiDocTemplateDO = iOpenplatformDocApiDocTemplateService.list(openplatformDocApiDocTemplateQueryListCommand);
		List<OpenplatformDocApiDocTemplateVO> openplatformDocApiDocTemplateVOs = OpenplatformDocApiDocTemplateAppStructMapping.instance.openplatformDocApiDocTemplateDOsToOpenplatformDocApiDocTemplateVOs(openplatformDocApiDocTemplateDO);
		return MultiResponse.of(openplatformDocApiDocTemplateVOs);
	}
	/**
	 * 执行 开放接口文档模板 分页查询指令
	 * @param openplatformDocApiDocTemplatePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateVO> execute(@Valid OpenplatformDocApiDocTemplatePageQueryCommand openplatformDocApiDocTemplatePageQueryCommand) {
		Page<OpenplatformDocApiDocTemplateDO> page = iOpenplatformDocApiDocTemplateService.listPage(openplatformDocApiDocTemplatePageQueryCommand);
		return OpenplatformDocApiDocTemplateAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档模板 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocTemplateDO byId = iOpenplatformDocApiDocTemplateService.getById(detailCommand.getId());
		OpenplatformDocApiDocTemplateVO openplatformDocApiDocTemplateVO = OpenplatformDocApiDocTemplateAppStructMapping.instance.openplatformDocApiDocTemplateDOToOpenplatformDocApiDocTemplateVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateVO);
	}
	/**
	 * 执行 开放接口文档模板 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocTemplateDO byId = iOpenplatformDocApiDocTemplateService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocTemplateVO openplatformDocApiDocTemplateVO = OpenplatformDocApiDocTemplateAppStructMapping.instance.openplatformDocApiDocTemplateDOToOpenplatformDocApiDocTemplateVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateService(IOpenplatformDocApiDocTemplateService iOpenplatformDocApiDocTemplateService) {
		this.iOpenplatformDocApiDocTemplateService = iOpenplatformDocApiDocTemplateService;
	}
}

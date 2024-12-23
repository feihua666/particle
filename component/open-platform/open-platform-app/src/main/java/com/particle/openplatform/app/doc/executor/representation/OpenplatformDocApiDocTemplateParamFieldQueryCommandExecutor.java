package com.particle.openplatform.app.doc.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocTemplateParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocTemplateParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocTemplateParamFieldVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocTemplateParamFieldDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocTemplateParamFieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档模板参数字段 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-18 16:48:56
 */
@Component
@Validated
public class OpenplatformDocApiDocTemplateParamFieldQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService;

	/**
	 * 执行 开放接口文档模板参数字段 列表查询指令
	 * @param openplatformDocApiDocTemplateParamFieldQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute(@Valid OpenplatformDocApiDocTemplateParamFieldQueryListCommand openplatformDocApiDocTemplateParamFieldQueryListCommand) {
		List<OpenplatformDocApiDocTemplateParamFieldDO> openplatformDocApiDocTemplateParamFieldDO = iOpenplatformDocApiDocTemplateParamFieldService.list(openplatformDocApiDocTemplateParamFieldQueryListCommand);
		List<OpenplatformDocApiDocTemplateParamFieldVO> openplatformDocApiDocTemplateParamFieldVOs = OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.openplatformDocApiDocTemplateParamFieldDOsToOpenplatformDocApiDocTemplateParamFieldVOs(openplatformDocApiDocTemplateParamFieldDO);
		return MultiResponse.of(openplatformDocApiDocTemplateParamFieldVOs);
	}
	/**
	 * 执行 开放接口文档模板参数字段 分页查询指令
	 * @param openplatformDocApiDocTemplateParamFieldPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocTemplateParamFieldVO> execute(@Valid OpenplatformDocApiDocTemplateParamFieldPageQueryCommand openplatformDocApiDocTemplateParamFieldPageQueryCommand) {
		Page<OpenplatformDocApiDocTemplateParamFieldDO> page = iOpenplatformDocApiDocTemplateParamFieldService.listPage(openplatformDocApiDocTemplateParamFieldPageQueryCommand);
		return OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档模板参数字段 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocTemplateParamFieldDO byId = iOpenplatformDocApiDocTemplateParamFieldService.getById(detailCommand.getId());
		OpenplatformDocApiDocTemplateParamFieldVO openplatformDocApiDocTemplateParamFieldVO = OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.openplatformDocApiDocTemplateParamFieldDOToOpenplatformDocApiDocTemplateParamFieldVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateParamFieldVO);
	}
	/**
	 * 执行 开放接口文档模板参数字段 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocTemplateParamFieldVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocTemplateParamFieldDO byId = iOpenplatformDocApiDocTemplateParamFieldService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocTemplateParamFieldVO openplatformDocApiDocTemplateParamFieldVO = OpenplatformDocApiDocTemplateParamFieldAppStructMapping.instance.openplatformDocApiDocTemplateParamFieldDOToOpenplatformDocApiDocTemplateParamFieldVO(byId);
		return SingleResponse.of(openplatformDocApiDocTemplateParamFieldVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocTemplateParamFieldService(IOpenplatformDocApiDocTemplateParamFieldService iOpenplatformDocApiDocTemplateParamFieldService) {
		this.iOpenplatformDocApiDocTemplateParamFieldService = iOpenplatformDocApiDocTemplateParamFieldService;
	}
}

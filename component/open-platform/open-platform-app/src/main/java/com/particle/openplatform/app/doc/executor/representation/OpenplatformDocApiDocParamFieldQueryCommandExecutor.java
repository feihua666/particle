package com.particle.openplatform.app.doc.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.doc.structmapping.OpenplatformDocApiDocParamFieldAppStructMapping;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldPageQueryCommand;
import com.particle.openplatform.client.doc.dto.command.representation.OpenplatformDocApiDocParamFieldQueryListCommand;
import com.particle.openplatform.client.doc.dto.data.OpenplatformDocApiDocParamFieldVO;
import com.particle.openplatform.infrastructure.doc.dos.OpenplatformDocApiDocParamFieldDO;
import com.particle.openplatform.infrastructure.doc.service.IOpenplatformDocApiDocParamFieldService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口文档参数字段 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-03-14 14:56:55
 */
@Component
@Validated
public class OpenplatformDocApiDocParamFieldQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService;

	/**
	 * 执行 开放接口文档参数字段 列表查询指令
	 * @param openplatformDocApiDocParamFieldQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformDocApiDocParamFieldVO> execute(@Valid OpenplatformDocApiDocParamFieldQueryListCommand openplatformDocApiDocParamFieldQueryListCommand) {
		List<OpenplatformDocApiDocParamFieldDO> openplatformDocApiDocParamFieldDO = iOpenplatformDocApiDocParamFieldService.list(openplatformDocApiDocParamFieldQueryListCommand);
		List<OpenplatformDocApiDocParamFieldVO> openplatformDocApiDocParamFieldVOs = OpenplatformDocApiDocParamFieldAppStructMapping.instance.openplatformDocApiDocParamFieldDOsToOpenplatformDocApiDocParamFieldVOs(openplatformDocApiDocParamFieldDO);
		return MultiResponse.of(openplatformDocApiDocParamFieldVOs);
	}
	/**
	 * 执行 开放接口文档参数字段 分页查询指令
	 * @param openplatformDocApiDocParamFieldPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformDocApiDocParamFieldVO> execute(@Valid OpenplatformDocApiDocParamFieldPageQueryCommand openplatformDocApiDocParamFieldPageQueryCommand) {
		Page<OpenplatformDocApiDocParamFieldDO> page = iOpenplatformDocApiDocParamFieldService.listPage(openplatformDocApiDocParamFieldPageQueryCommand);
		return OpenplatformDocApiDocParamFieldAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口文档参数字段 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> executeDetail(IdCommand detailCommand) {
		OpenplatformDocApiDocParamFieldDO byId = iOpenplatformDocApiDocParamFieldService.getById(detailCommand.getId());
		OpenplatformDocApiDocParamFieldVO openplatformDocApiDocParamFieldVO = OpenplatformDocApiDocParamFieldAppStructMapping.instance.openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldVO(byId);
		return SingleResponse.of(openplatformDocApiDocParamFieldVO);
	}
	/**
	 * 执行 开放接口文档参数字段 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformDocApiDocParamFieldVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformDocApiDocParamFieldDO byId = iOpenplatformDocApiDocParamFieldService.getById(detailForUpdateCommand.getId());
		OpenplatformDocApiDocParamFieldVO openplatformDocApiDocParamFieldVO = OpenplatformDocApiDocParamFieldAppStructMapping.instance.openplatformDocApiDocParamFieldDOToOpenplatformDocApiDocParamFieldVO(byId);
		return SingleResponse.of(openplatformDocApiDocParamFieldVO);
	}

	@Autowired
	public void setIOpenplatformDocApiDocParamFieldService(IOpenplatformDocApiDocParamFieldService iOpenplatformDocApiDocParamFieldService) {
		this.iOpenplatformDocApiDocParamFieldService = iOpenplatformDocApiDocParamFieldService;
	}
}

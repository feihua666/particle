package com.particle.openplatform.app.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppOpenapiAppStructMapping;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppOpenapiQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppOpenapiVO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppOpenapiDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppOpenapiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台应用与开放接口配置 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-05 11:49:06
 */
@Component
@Validated
public class OpenplatformAppOpenapiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService;

	/**
	 * 执行 开放平台应用与开放接口配置 列表查询指令
	 * @param openplatformAppOpenapiQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformAppOpenapiVO> execute(@Valid OpenplatformAppOpenapiQueryListCommand openplatformAppOpenapiQueryListCommand) {
		List<OpenplatformAppOpenapiDO> openplatformAppOpenapiDO = iOpenplatformAppOpenapiService.list(openplatformAppOpenapiQueryListCommand);
		List<OpenplatformAppOpenapiVO> openplatformAppOpenapiVOs = OpenplatformAppOpenapiAppStructMapping.instance.openplatformAppOpenapiDOsToOpenplatformAppOpenapiVOs(openplatformAppOpenapiDO);
		return MultiResponse.of(openplatformAppOpenapiVOs);
	}
	/**
	 * 执行 开放平台应用与开放接口配置 分页查询指令
	 * @param openplatformAppOpenapiPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformAppOpenapiVO> execute(@Valid OpenplatformAppOpenapiPageQueryCommand openplatformAppOpenapiPageQueryCommand) {
		Page<OpenplatformAppOpenapiDO> page = iOpenplatformAppOpenapiService.listPage(openplatformAppOpenapiPageQueryCommand);
		return OpenplatformAppOpenapiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用与开放接口配置 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppOpenapiVO> executeDetail(IdCommand detailCommand) {
		OpenplatformAppOpenapiDO byId = iOpenplatformAppOpenapiService.getById(detailCommand.getId());
		OpenplatformAppOpenapiVO openplatformAppOpenapiVO = OpenplatformAppOpenapiAppStructMapping.instance.openplatformAppOpenapiDOToOpenplatformAppOpenapiVO(byId);
		return SingleResponse.of(openplatformAppOpenapiVO);
	}
	/**
	 * 执行 开放平台应用与开放接口配置 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppOpenapiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformAppOpenapiDO byId = iOpenplatformAppOpenapiService.getById(detailForUpdateCommand.getId());
		OpenplatformAppOpenapiVO openplatformAppOpenapiVO = OpenplatformAppOpenapiAppStructMapping.instance.openplatformAppOpenapiDOToOpenplatformAppOpenapiVO(byId);
		return SingleResponse.of(openplatformAppOpenapiVO);
	}

	@Autowired
	public void setIOpenplatformAppOpenapiService(IOpenplatformAppOpenapiService iOpenplatformAppOpenapiService) {
		this.iOpenplatformAppOpenapiService = iOpenplatformAppOpenapiService;
	}
}

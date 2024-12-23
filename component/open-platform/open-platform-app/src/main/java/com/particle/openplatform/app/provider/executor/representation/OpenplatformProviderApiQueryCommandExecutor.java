package com.particle.openplatform.app.provider.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.provider.structmapping.OpenplatformProviderApiAppStructMapping;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiPageQueryCommand;
import com.particle.openplatform.client.provider.dto.command.representation.OpenplatformProviderApiQueryListCommand;
import com.particle.openplatform.client.provider.dto.data.OpenplatformProviderApiVO;
import com.particle.openplatform.infrastructure.provider.dos.OpenplatformProviderApiDO;
import com.particle.openplatform.infrastructure.provider.service.IOpenplatformProviderApiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台供应商接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:53:52
 */
@Component
@Validated
public class OpenplatformProviderApiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderApiService iOpenplatformProviderApiService;

	/**
	 * 执行 开放平台供应商接口 列表查询指令
	 * @param openplatformProviderApiQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderApiVO> execute(@Valid OpenplatformProviderApiQueryListCommand openplatformProviderApiQueryListCommand) {
		List<OpenplatformProviderApiDO> openplatformProviderApiDO = iOpenplatformProviderApiService.list(openplatformProviderApiQueryListCommand);
		List<OpenplatformProviderApiVO> openplatformProviderApiVOs = OpenplatformProviderApiAppStructMapping.instance.openplatformProviderApiDOsToOpenplatformProviderApiVOs(openplatformProviderApiDO);
		return MultiResponse.of(openplatformProviderApiVOs);
	}
	/**
	 * 执行 开放平台供应商接口 分页查询指令
	 * @param openplatformProviderApiPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderApiVO> execute(@Valid OpenplatformProviderApiPageQueryCommand openplatformProviderApiPageQueryCommand) {
		Page<OpenplatformProviderApiDO> page = iOpenplatformProviderApiService.listPage(openplatformProviderApiPageQueryCommand);
		return OpenplatformProviderApiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台供应商接口 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderApiVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderApiDO byId = iOpenplatformProviderApiService.getById(detailCommand.getId());
		OpenplatformProviderApiVO openplatformProviderApiVO = OpenplatformProviderApiAppStructMapping.instance.openplatformProviderApiDOToOpenplatformProviderApiVO(byId);
		return SingleResponse.of(openplatformProviderApiVO);
	}
	/**
	 * 执行 开放平台供应商接口 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderApiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderApiDO byId = iOpenplatformProviderApiService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderApiVO openplatformProviderApiVO = OpenplatformProviderApiAppStructMapping.instance.openplatformProviderApiDOToOpenplatformProviderApiVO(byId);
		return SingleResponse.of(openplatformProviderApiVO);
	}


	@Autowired
	public void setIOpenplatformProviderApiService(IOpenplatformProviderApiService iOpenplatformProviderApiService) {
		this.iOpenplatformProviderApiService = iOpenplatformProviderApiService;
	}
}

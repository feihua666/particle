package com.particle.openplatform.app.openapi.executor.representation;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import javax.validation.Valid;
import java.util.List;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;

/**
 * <p>
 * 开放平台开放接口 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-08 11:13:24
 */
@Component
@Validated
public class OpenplatformOpenapiQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiService iOpenplatformOpenapiService;

	/**
	 * 执行 开放平台开放接口 列表查询指令
	 * @param openplatformOpenapiQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiVO> execute(@Valid OpenplatformOpenapiQueryListCommand openplatformOpenapiQueryListCommand) {
		List<OpenplatformOpenapiDO> openplatformOpenapiDO = iOpenplatformOpenapiService.list(openplatformOpenapiQueryListCommand);
		List<OpenplatformOpenapiVO> openplatformOpenapiVOs = OpenplatformOpenapiAppStructMapping.instance.openplatformOpenapiDOsToOpenplatformOpenapiVOs(openplatformOpenapiDO);
		return MultiResponse.of(openplatformOpenapiVOs);
	}
	/**
	 * 执行 开放平台开放接口 分页查询指令
	 * @param openplatformOpenapiPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiVO> execute(@Valid OpenplatformOpenapiPageQueryCommand openplatformOpenapiPageQueryCommand) {
		Page<OpenplatformOpenapiDO> page = iOpenplatformOpenapiService.listPage(openplatformOpenapiPageQueryCommand);
		return OpenplatformOpenapiAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiDO byId = iOpenplatformOpenapiService.getById(detailCommand.getId());
		OpenplatformOpenapiVO openplatformOpenapiVO = OpenplatformOpenapiAppStructMapping.instance.openplatformOpenapiDOToOpenplatformOpenapiVO(byId);
		return SingleResponse.of(openplatformOpenapiVO);
	}
	/**
	 * 执行 开放平台开放接口 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiDO byId = iOpenplatformOpenapiService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiVO openplatformOpenapiVO = OpenplatformOpenapiAppStructMapping.instance.openplatformOpenapiDOToOpenplatformOpenapiVO(byId);
		return SingleResponse.of(openplatformOpenapiVO);
	}

	@Autowired
	public void setIOpenplatformOpenapiService(IOpenplatformOpenapiService iOpenplatformOpenapiService) {
		this.iOpenplatformOpenapiService = iOpenplatformOpenapiService;
	}
}

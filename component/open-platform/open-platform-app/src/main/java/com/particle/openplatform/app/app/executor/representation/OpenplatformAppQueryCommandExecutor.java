package com.particle.openplatform.app.app.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.app.structmapping.OpenplatformAppAppStructMapping;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppPageQueryCommand;
import com.particle.openplatform.client.app.dto.command.representation.OpenplatformAppQueryListCommand;
import com.particle.openplatform.client.app.dto.data.OpenplatformAppVO;
import com.particle.openplatform.infrastructure.app.dos.OpenplatformAppDO;
import com.particle.openplatform.infrastructure.app.service.IOpenplatformAppService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台应用 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-05 11:40:15
 */
@Component
@Validated
public class OpenplatformAppQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformAppService iOpenplatformAppService;

	/**
	 * 执行 开放平台应用 列表查询指令
	 * @param openplatformAppQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformAppVO> execute(@Valid OpenplatformAppQueryListCommand openplatformAppQueryListCommand) {
		List<OpenplatformAppDO> openplatformAppDO = iOpenplatformAppService.list(openplatformAppQueryListCommand);
		List<OpenplatformAppVO> openplatformAppVOs = OpenplatformAppAppStructMapping.instance.openplatformAppDOsToOpenplatformAppVOs(openplatformAppDO);
		return MultiResponse.of(openplatformAppVOs);
	}
	/**
	 * 执行 开放平台应用 分页查询指令
	 * @param openplatformAppPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformAppVO> execute(@Valid OpenplatformAppPageQueryCommand openplatformAppPageQueryCommand) {
		Page<OpenplatformAppDO> page = iOpenplatformAppService.listPage(openplatformAppPageQueryCommand);
		return OpenplatformAppAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppVO> executeDetail(IdCommand detailCommand) {
		OpenplatformAppDO byId = iOpenplatformAppService.getById(detailCommand.getId());
		OpenplatformAppVO openplatformAppVO = OpenplatformAppAppStructMapping.instance.openplatformAppDOToOpenplatformAppVO(byId);
		return SingleResponse.of(openplatformAppVO);
	}
	/**
	 * 执行 开放平台应用 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformAppVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformAppDO byId = iOpenplatformAppService.getById(detailForUpdateCommand.getId());
		OpenplatformAppVO openplatformAppVO = OpenplatformAppAppStructMapping.instance.openplatformAppDOToOpenplatformAppVO(byId);
		return SingleResponse.of(openplatformAppVO);
	}

	@Autowired
	public void setIOpenplatformAppService(IOpenplatformAppService iOpenplatformAppService) {
		this.iOpenplatformAppService = iOpenplatformAppService;
	}
}

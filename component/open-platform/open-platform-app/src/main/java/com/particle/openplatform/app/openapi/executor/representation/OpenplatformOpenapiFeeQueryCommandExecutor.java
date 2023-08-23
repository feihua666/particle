package com.particle.openplatform.app.openapi.executor.representation;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiFeeAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeeQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiFeeVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiFeeDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiFeeService;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiFeePageQueryCommand;
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
 * 开放平台开放接口费用 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-05 11:59:32
 */
@Component
@Validated
public class OpenplatformOpenapiFeeQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiFeeService iOpenplatformOpenapiFeeService;

	/**
	 * 执行 开放平台开放接口费用 列表查询指令
	 * @param openplatformOpenapiFeeQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiFeeVO> execute(@Valid OpenplatformOpenapiFeeQueryListCommand openplatformOpenapiFeeQueryListCommand) {
		List<OpenplatformOpenapiFeeDO> openplatformOpenapiFeeDO = iOpenplatformOpenapiFeeService.list(openplatformOpenapiFeeQueryListCommand);
		List<OpenplatformOpenapiFeeVO> openplatformOpenapiFeeVOs = OpenplatformOpenapiFeeAppStructMapping.instance.openplatformOpenapiFeeDOsToOpenplatformOpenapiFeeVOs(openplatformOpenapiFeeDO);
		return MultiResponse.of(openplatformOpenapiFeeVOs);
	}
	/**
	 * 执行 开放平台开放接口费用 分页查询指令
	 * @param openplatformOpenapiFeePageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiFeeVO> execute(@Valid OpenplatformOpenapiFeePageQueryCommand openplatformOpenapiFeePageQueryCommand) {
		Page<OpenplatformOpenapiFeeDO> page = iOpenplatformOpenapiFeeService.listPage(openplatformOpenapiFeePageQueryCommand);
		return OpenplatformOpenapiFeeAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口费用 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiFeeVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiFeeDO byId = iOpenplatformOpenapiFeeService.getById(detailCommand.getId());
		OpenplatformOpenapiFeeVO openplatformOpenapiFeeVO = OpenplatformOpenapiFeeAppStructMapping.instance.openplatformOpenapiFeeDOToOpenplatformOpenapiFeeVO(byId);
		return SingleResponse.of(openplatformOpenapiFeeVO);
	}
	/**
	 * 执行 开放平台开放接口费用 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiFeeVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiFeeDO byId = iOpenplatformOpenapiFeeService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiFeeVO openplatformOpenapiFeeVO = OpenplatformOpenapiFeeAppStructMapping.instance.openplatformOpenapiFeeDOToOpenplatformOpenapiFeeVO(byId);
		return SingleResponse.of(openplatformOpenapiFeeVO);
	}

	@Autowired
	public void setIOpenplatformOpenapiFeeService(IOpenplatformOpenapiFeeService iOpenplatformOpenapiFeeService) {
		this.iOpenplatformOpenapiFeeService = iOpenplatformOpenapiFeeService;
	}
}

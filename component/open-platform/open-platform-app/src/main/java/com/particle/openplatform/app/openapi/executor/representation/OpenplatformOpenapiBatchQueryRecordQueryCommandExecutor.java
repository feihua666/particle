package com.particle.openplatform.app.openapi.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordPageQueryCommand;
import com.particle.openplatform.client.openapi.dto.command.representation.OpenplatformOpenapiBatchQueryRecordQueryListCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.infrastructure.openapi.dos.OpenplatformOpenapiBatchQueryRecordDO;
import com.particle.openplatform.infrastructure.openapi.service.IOpenplatformOpenapiBatchQueryRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放接口批量查询记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService;

	/**
	 * 执行 开放接口批量查询记录 列表查询指令
	 * @param openplatformOpenapiBatchQueryRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiBatchQueryRecordVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordQueryListCommand openplatformOpenapiBatchQueryRecordQueryListCommand) {
		List<OpenplatformOpenapiBatchQueryRecordDO> openplatformOpenapiBatchQueryRecordDO = iOpenplatformOpenapiBatchQueryRecordService.list(openplatformOpenapiBatchQueryRecordQueryListCommand);
		List<OpenplatformOpenapiBatchQueryRecordVO> openplatformOpenapiBatchQueryRecordVOs = OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.openplatformOpenapiBatchQueryRecordDOsToOpenplatformOpenapiBatchQueryRecordVOs(openplatformOpenapiBatchQueryRecordDO);
		return MultiResponse.of(openplatformOpenapiBatchQueryRecordVOs);
	}
	/**
	 * 执行 开放接口批量查询记录 分页查询指令
	 * @param openplatformOpenapiBatchQueryRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiBatchQueryRecordVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordPageQueryCommand openplatformOpenapiBatchQueryRecordPageQueryCommand) {
		Page<OpenplatformOpenapiBatchQueryRecordDO> page = iOpenplatformOpenapiBatchQueryRecordService.listPage(openplatformOpenapiBatchQueryRecordPageQueryCommand);
		return OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放接口批量查询记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiBatchQueryRecordDO byId = iOpenplatformOpenapiBatchQueryRecordService.getById(detailCommand.getId());
		OpenplatformOpenapiBatchQueryRecordVO openplatformOpenapiBatchQueryRecordVO = OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.openplatformOpenapiBatchQueryRecordDOToOpenplatformOpenapiBatchQueryRecordVO(byId);
		return SingleResponse.of(openplatformOpenapiBatchQueryRecordVO);
	}
	/**
	 * 执行 开放接口批量查询记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiBatchQueryRecordDO byId = iOpenplatformOpenapiBatchQueryRecordService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiBatchQueryRecordVO openplatformOpenapiBatchQueryRecordVO = OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.openplatformOpenapiBatchQueryRecordDOToOpenplatformOpenapiBatchQueryRecordVO(byId);
		return SingleResponse.of(openplatformOpenapiBatchQueryRecordVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiBatchQueryRecordService(IOpenplatformOpenapiBatchQueryRecordService iOpenplatformOpenapiBatchQueryRecordService) {
		this.iOpenplatformOpenapiBatchQueryRecordService = iOpenplatformOpenapiBatchQueryRecordService;
	}
}

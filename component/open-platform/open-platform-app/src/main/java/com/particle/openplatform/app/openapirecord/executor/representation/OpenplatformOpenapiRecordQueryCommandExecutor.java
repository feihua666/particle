package com.particle.openplatform.app.openapirecord.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.openapirecord.structmapping.OpenplatformOpenapiRecordAppStructMapping;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordPageQueryCommand;
import com.particle.openplatform.client.openapirecord.dto.command.representation.OpenplatformOpenapiRecordQueryListCommand;
import com.particle.openplatform.client.openapirecord.dto.data.OpenplatformOpenapiRecordVO;
import com.particle.openplatform.infrastructure.openapirecord.dos.OpenplatformOpenapiRecordDO;
import com.particle.openplatform.infrastructure.openapirecord.service.IOpenplatformOpenapiRecordService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台开放接口调用记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-16 16:13:46
 */
@Component
@Validated
public class OpenplatformOpenapiRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService;

	/**
	 * 执行 开放平台开放接口调用记录 列表查询指令
	 * @param openplatformOpenapiRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordVO> execute(@Valid OpenplatformOpenapiRecordQueryListCommand openplatformOpenapiRecordQueryListCommand) {
		List<OpenplatformOpenapiRecordDO> openplatformOpenapiRecordDO = iOpenplatformOpenapiRecordService.list(openplatformOpenapiRecordQueryListCommand);
		List<OpenplatformOpenapiRecordVO> openplatformOpenapiRecordVOs = OpenplatformOpenapiRecordAppStructMapping.instance.openplatformOpenapiRecordDOsToOpenplatformOpenapiRecordVOs(openplatformOpenapiRecordDO);
		return MultiResponse.of(openplatformOpenapiRecordVOs);
	}
	/**
	 * 执行 开放平台开放接口调用记录 分页查询指令
	 * @param openplatformOpenapiRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordVO> execute(@Valid OpenplatformOpenapiRecordPageQueryCommand openplatformOpenapiRecordPageQueryCommand) {
		Page<OpenplatformOpenapiRecordDO> page = iOpenplatformOpenapiRecordService.listPage(openplatformOpenapiRecordPageQueryCommand);
		return OpenplatformOpenapiRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口调用记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordDO byId = iOpenplatformOpenapiRecordService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordVO openplatformOpenapiRecordVO = OpenplatformOpenapiRecordAppStructMapping.instance.openplatformOpenapiRecordDOToOpenplatformOpenapiRecordVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordVO);
	}
	/**
	 * 执行 开放平台开放接口调用记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiRecordDO byId = iOpenplatformOpenapiRecordService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiRecordVO openplatformOpenapiRecordVO = OpenplatformOpenapiRecordAppStructMapping.instance.openplatformOpenapiRecordDOToOpenplatformOpenapiRecordVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordVO);
	}

	@Autowired
	public void setIOpenplatformOpenapiRecordService(IOpenplatformOpenapiRecordService iOpenplatformOpenapiRecordService) {
		this.iOpenplatformOpenapiRecordService = iOpenplatformOpenapiRecordService;
	}
}

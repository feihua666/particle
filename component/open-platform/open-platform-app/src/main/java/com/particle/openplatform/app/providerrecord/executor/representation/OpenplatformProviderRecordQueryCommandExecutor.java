package com.particle.openplatform.app.providerrecord.executor.representation;

import com.particle.openplatform.app.providerrecord.structmapping.OpenplatformProviderRecordAppStructMapping;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordQueryListCommand;
import com.particle.openplatform.client.providerrecord.dto.data.OpenplatformProviderRecordVO;
import com.particle.openplatform.infrastructure.providerrecord.dos.OpenplatformProviderRecordDO;
import com.particle.openplatform.infrastructure.providerrecord.service.IOpenplatformProviderRecordService;
import com.particle.openplatform.client.providerrecord.dto.command.representation.OpenplatformProviderRecordPageQueryCommand;
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
 * 开放平台开放接口供应商调用记录 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2023-08-16 16:17:25
 */
@Component
@Validated
public class OpenplatformProviderRecordQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderRecordService iOpenplatformProviderRecordService;

	/**
	 * 执行 开放平台开放接口供应商调用记录 列表查询指令
	 * @param openplatformProviderRecordQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderRecordVO> execute(@Valid OpenplatformProviderRecordQueryListCommand openplatformProviderRecordQueryListCommand) {
		List<OpenplatformProviderRecordDO> openplatformProviderRecordDO = iOpenplatformProviderRecordService.list(openplatformProviderRecordQueryListCommand);
		List<OpenplatformProviderRecordVO> openplatformProviderRecordVOs = OpenplatformProviderRecordAppStructMapping.instance.openplatformProviderRecordDOsToOpenplatformProviderRecordVOs(openplatformProviderRecordDO);
		return MultiResponse.of(openplatformProviderRecordVOs);
	}
	/**
	 * 执行 开放平台开放接口供应商调用记录 分页查询指令
	 * @param openplatformProviderRecordPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordVO> execute(@Valid OpenplatformProviderRecordPageQueryCommand openplatformProviderRecordPageQueryCommand) {
		Page<OpenplatformProviderRecordDO> page = iOpenplatformProviderRecordService.listPage(openplatformProviderRecordPageQueryCommand);
		return OpenplatformProviderRecordAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台开放接口供应商调用记录 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderRecordDO byId = iOpenplatformProviderRecordService.getById(detailCommand.getId());
		OpenplatformProviderRecordVO openplatformProviderRecordVO = OpenplatformProviderRecordAppStructMapping.instance.openplatformProviderRecordDOToOpenplatformProviderRecordVO(byId);
		return SingleResponse.of(openplatformProviderRecordVO);
	}
	/**
	 * 执行 开放平台开放接口供应商调用记录 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderRecordDO byId = iOpenplatformProviderRecordService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderRecordVO openplatformProviderRecordVO = OpenplatformProviderRecordAppStructMapping.instance.openplatformProviderRecordDOToOpenplatformProviderRecordVO(byId);
		return SingleResponse.of(openplatformProviderRecordVO);
	}

	@Autowired
	public void setIOpenplatformProviderRecordService(IOpenplatformProviderRecordService iOpenplatformProviderRecordService) {
		this.iOpenplatformProviderRecordService = iOpenplatformProviderRecordService;
	}
}

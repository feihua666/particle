package com.particle.openplatform.app.bill.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiDaySummaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台供应商接口日汇总 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiDaySummaryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService;

	/**
	 * 执行 开放平台供应商接口日汇总 列表查询指令
	 * @param openplatformProviderRecordPrdApiDaySummaryQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiDaySummaryQueryListCommand openplatformProviderRecordPrdApiDaySummaryQueryListCommand) {
		List<OpenplatformProviderRecordPrdApiDaySummaryDO> openplatformProviderRecordPrdApiDaySummaryDO = iOpenplatformProviderRecordPrdApiDaySummaryService.list(openplatformProviderRecordPrdApiDaySummaryQueryListCommand);
		List<OpenplatformProviderRecordPrdApiDaySummaryVO> openplatformProviderRecordPrdApiDaySummaryVOs = OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.openplatformProviderRecordPrdApiDaySummaryDOsToOpenplatformProviderRecordPrdApiDaySummaryVOs(openplatformProviderRecordPrdApiDaySummaryDO);
		return MultiResponse.of(openplatformProviderRecordPrdApiDaySummaryVOs);
	}
	/**
	 * 执行 开放平台供应商接口日汇总 分页查询指令
	 * @param openplatformProviderRecordPrdApiDaySummaryPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiDaySummaryPageQueryCommand openplatformProviderRecordPrdApiDaySummaryPageQueryCommand) {
		Page<OpenplatformProviderRecordPrdApiDaySummaryDO> page = iOpenplatformProviderRecordPrdApiDaySummaryService.listPage(openplatformProviderRecordPrdApiDaySummaryPageQueryCommand);
		return OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台供应商接口日汇总 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderRecordPrdApiDaySummaryDO byId = iOpenplatformProviderRecordPrdApiDaySummaryService.getById(detailCommand.getId());
		OpenplatformProviderRecordPrdApiDaySummaryVO openplatformProviderRecordPrdApiDaySummaryVO = OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.openplatformProviderRecordPrdApiDaySummaryDOToOpenplatformProviderRecordPrdApiDaySummaryVO(byId);
		return SingleResponse.of(openplatformProviderRecordPrdApiDaySummaryVO);
	}
	/**
	 * 执行 开放平台供应商接口日汇总 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderRecordPrdApiDaySummaryDO byId = iOpenplatformProviderRecordPrdApiDaySummaryService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderRecordPrdApiDaySummaryVO openplatformProviderRecordPrdApiDaySummaryVO = OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.openplatformProviderRecordPrdApiDaySummaryDOToOpenplatformProviderRecordPrdApiDaySummaryVO(byId);
		return SingleResponse.of(openplatformProviderRecordPrdApiDaySummaryVO);
	}


	@Autowired
	public void setIOpenplatformProviderRecordPrdApiDaySummaryService(IOpenplatformProviderRecordPrdApiDaySummaryService iOpenplatformProviderRecordPrdApiDaySummaryService) {
		this.iOpenplatformProviderRecordPrdApiDaySummaryService = iOpenplatformProviderRecordPrdApiDaySummaryService;
	}
}

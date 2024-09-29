package com.particle.openplatform.app.bill.executor.representation;

import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdApiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdApiMonthSummaryService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.global.dto.response.MultiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.PageResponse;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.global.dto.response.SingleResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 开放平台供应商接口月汇总 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiMonthSummaryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService;

	/**
	 * 执行 开放平台供应商接口月汇总 列表查询指令
	 * @param openplatformProviderRecordPrdApiMonthSummaryQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiMonthSummaryQueryListCommand openplatformProviderRecordPrdApiMonthSummaryQueryListCommand) {
		List<OpenplatformProviderRecordPrdApiMonthSummaryDO> openplatformProviderRecordPrdApiMonthSummaryDO = iOpenplatformProviderRecordPrdApiMonthSummaryService.list(openplatformProviderRecordPrdApiMonthSummaryQueryListCommand);
		List<OpenplatformProviderRecordPrdApiMonthSummaryVO> openplatformProviderRecordPrdApiMonthSummaryVOs = OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.openplatformProviderRecordPrdApiMonthSummaryDOsToOpenplatformProviderRecordPrdApiMonthSummaryVOs(openplatformProviderRecordPrdApiMonthSummaryDO);
		return MultiResponse.of(openplatformProviderRecordPrdApiMonthSummaryVOs);
	}
	/**
	 * 执行 开放平台供应商接口月汇总 分页查询指令
	 * @param openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiMonthSummaryPageQueryCommand openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand) {
		Page<OpenplatformProviderRecordPrdApiMonthSummaryDO> page = iOpenplatformProviderRecordPrdApiMonthSummaryService.listPage(openplatformProviderRecordPrdApiMonthSummaryPageQueryCommand);
		return OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台供应商接口月汇总 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderRecordPrdApiMonthSummaryDO byId = iOpenplatformProviderRecordPrdApiMonthSummaryService.getById(detailCommand.getId());
		OpenplatformProviderRecordPrdApiMonthSummaryVO openplatformProviderRecordPrdApiMonthSummaryVO = OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.openplatformProviderRecordPrdApiMonthSummaryDOToOpenplatformProviderRecordPrdApiMonthSummaryVO(byId);
		return SingleResponse.of(openplatformProviderRecordPrdApiMonthSummaryVO);
	}
	/**
	 * 执行 开放平台供应商接口月汇总 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderRecordPrdApiMonthSummaryDO byId = iOpenplatformProviderRecordPrdApiMonthSummaryService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderRecordPrdApiMonthSummaryVO openplatformProviderRecordPrdApiMonthSummaryVO = OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.openplatformProviderRecordPrdApiMonthSummaryDOToOpenplatformProviderRecordPrdApiMonthSummaryVO(byId);
		return SingleResponse.of(openplatformProviderRecordPrdApiMonthSummaryVO);
	}


	@Autowired
	public void setIOpenplatformProviderRecordPrdApiMonthSummaryService(IOpenplatformProviderRecordPrdApiMonthSummaryService iOpenplatformProviderRecordPrdApiMonthSummaryService) {
		this.iOpenplatformProviderRecordPrdApiMonthSummaryService = iOpenplatformProviderRecordPrdApiMonthSummaryService;
	}
}

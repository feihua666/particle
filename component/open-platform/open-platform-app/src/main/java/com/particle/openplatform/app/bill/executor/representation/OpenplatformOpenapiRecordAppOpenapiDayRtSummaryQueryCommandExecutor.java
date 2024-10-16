package com.particle.openplatform.app.bill.executor.representation;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand;
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
 * 开放平台应用开放接口日实时汇总 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-15 10:30:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;

	/**
	 * 执行 开放平台应用开放接口日实时汇总 列表查询指令
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand) {
		List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> openplatformOpenapiRecordAppOpenapiDayRtSummaryDO = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.list(openplatformOpenapiRecordAppOpenapiDayRtSummaryQueryListCommand);
		List<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> openplatformOpenapiRecordAppOpenapiDayRtSummaryVOs = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiDayRtSummaryDOsToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVOs(openplatformOpenapiRecordAppOpenapiDayRtSummaryDO);
		return MultiResponse.of(openplatformOpenapiRecordAppOpenapiDayRtSummaryVOs);
	}
	/**
	 * 执行 开放平台应用开放接口日实时汇总 分页查询指令
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand) {
		Page<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO> page = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.listPage(openplatformOpenapiRecordAppOpenapiDayRtSummaryPageQueryCommand);
		return OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用开放接口日实时汇总 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO openplatformOpenapiRecordAppOpenapiDayRtSummaryVO = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiDayRtSummaryDOToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppOpenapiDayRtSummaryVO);
	}
	/**
	 * 执行 开放平台应用开放接口日实时汇总 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO openplatformOpenapiRecordAppOpenapiDayRtSummaryVO = OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiDayRtSummaryDOToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppOpenapiDayRtSummaryVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService(IOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService = iOpenplatformOpenapiRecordAppOpenapiDayRtSummaryService;
	}
}

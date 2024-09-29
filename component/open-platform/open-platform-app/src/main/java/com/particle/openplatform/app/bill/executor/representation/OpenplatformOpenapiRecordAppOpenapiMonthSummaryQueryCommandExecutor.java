package com.particle.openplatform.app.bill.executor.representation;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand;
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
 * 开放平台应用开放接口月汇总 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;

	/**
	 * 执行 开放平台应用开放接口月汇总 列表查询指令
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand) {
		List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> openplatformOpenapiRecordAppOpenapiMonthSummaryDO = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.list(openplatformOpenapiRecordAppOpenapiMonthSummaryQueryListCommand);
		List<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> openplatformOpenapiRecordAppOpenapiMonthSummaryVOs = OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiMonthSummaryDOsToOpenplatformOpenapiRecordAppOpenapiMonthSummaryVOs(openplatformOpenapiRecordAppOpenapiMonthSummaryDO);
		return MultiResponse.of(openplatformOpenapiRecordAppOpenapiMonthSummaryVOs);
	}
	/**
	 * 执行 开放平台应用开放接口月汇总 分页查询指令
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand) {
		Page<OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO> page = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.listPage(openplatformOpenapiRecordAppOpenapiMonthSummaryPageQueryCommand);
		return OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用开放接口月汇总 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO openplatformOpenapiRecordAppOpenapiMonthSummaryVO = OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiMonthSummaryDOToOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppOpenapiMonthSummaryVO);
	}
	/**
	 * 执行 开放平台应用开放接口月汇总 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO openplatformOpenapiRecordAppOpenapiMonthSummaryVO = OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiMonthSummaryDOToOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppOpenapiMonthSummaryVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiMonthSummaryService(IOpenplatformOpenapiRecordAppOpenapiMonthSummaryService iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService = iOpenplatformOpenapiRecordAppOpenapiMonthSummaryService;
	}
}

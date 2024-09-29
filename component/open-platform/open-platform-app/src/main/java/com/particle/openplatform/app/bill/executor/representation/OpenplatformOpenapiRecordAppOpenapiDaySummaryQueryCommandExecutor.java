package com.particle.openplatform.app.bill.executor.representation;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppOpenapiDaySummaryDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand;
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
 * 开放平台应用开放接口日汇总 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;

	/**
	 * 执行 开放平台应用开放接口日汇总 列表查询指令
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand) {
		List<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> openplatformOpenapiRecordAppOpenapiDaySummaryDO = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.list(openplatformOpenapiRecordAppOpenapiDaySummaryQueryListCommand);
		List<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> openplatformOpenapiRecordAppOpenapiDaySummaryVOs = OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiDaySummaryDOsToOpenplatformOpenapiRecordAppOpenapiDaySummaryVOs(openplatformOpenapiRecordAppOpenapiDaySummaryDO);
		return MultiResponse.of(openplatformOpenapiRecordAppOpenapiDaySummaryVOs);
	}
	/**
	 * 执行 开放平台应用开放接口日汇总 分页查询指令
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand) {
		Page<OpenplatformOpenapiRecordAppOpenapiDaySummaryDO> page = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.listPage(openplatformOpenapiRecordAppOpenapiDaySummaryPageQueryCommand);
		return OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用开放接口日汇总 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordAppOpenapiDaySummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiDaySummaryVO openplatformOpenapiRecordAppOpenapiDaySummaryVO = OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiDaySummaryDOToOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppOpenapiDaySummaryVO);
	}
	/**
	 * 执行 开放平台应用开放接口日汇总 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiRecordAppOpenapiDaySummaryDO byId = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiRecordAppOpenapiDaySummaryVO openplatformOpenapiRecordAppOpenapiDaySummaryVO = OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.openplatformOpenapiRecordAppOpenapiDaySummaryDOToOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppOpenapiDaySummaryVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiRecordAppOpenapiDaySummaryService(IOpenplatformOpenapiRecordAppOpenapiDaySummaryService iOpenplatformOpenapiRecordAppOpenapiDaySummaryService) {
		this.iOpenplatformOpenapiRecordAppOpenapiDaySummaryService = iOpenplatformOpenapiRecordAppOpenapiDaySummaryService;
	}
}

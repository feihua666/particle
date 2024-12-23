package com.particle.openplatform.app.bill.executor.representation;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.particle.common.app.executor.query.AbstractBaseQueryExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.MultiResponse;
import com.particle.global.dto.response.PageResponse;
import com.particle.global.dto.response.SingleResponse;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillPageQueryCommand;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordAppMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordAppMonthBillDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppMonthBillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

/**
 * <p>
 * 开放平台应用月账单 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppMonthBillQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService;

	/**
	 * 执行 开放平台应用月账单 列表查询指令
	 * @param openplatformOpenapiRecordAppMonthBillQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordAppMonthBillVO> execute(@Valid OpenplatformOpenapiRecordAppMonthBillQueryListCommand openplatformOpenapiRecordAppMonthBillQueryListCommand) {
		List<OpenplatformOpenapiRecordAppMonthBillDO> openplatformOpenapiRecordAppMonthBillDO = iOpenplatformOpenapiRecordAppMonthBillService.list(openplatformOpenapiRecordAppMonthBillQueryListCommand);
		List<OpenplatformOpenapiRecordAppMonthBillVO> openplatformOpenapiRecordAppMonthBillVOs = OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.openplatformOpenapiRecordAppMonthBillDOsToOpenplatformOpenapiRecordAppMonthBillVOs(openplatformOpenapiRecordAppMonthBillDO);
		return MultiResponse.of(openplatformOpenapiRecordAppMonthBillVOs);
	}
	/**
	 * 执行 开放平台应用月账单 分页查询指令
	 * @param openplatformOpenapiRecordAppMonthBillPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordAppMonthBillVO> execute(@Valid OpenplatformOpenapiRecordAppMonthBillPageQueryCommand openplatformOpenapiRecordAppMonthBillPageQueryCommand) {
		Page<OpenplatformOpenapiRecordAppMonthBillDO> page = iOpenplatformOpenapiRecordAppMonthBillService.listPage(openplatformOpenapiRecordAppMonthBillPageQueryCommand);
		return OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台应用月账单 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordAppMonthBillDO byId = iOpenplatformOpenapiRecordAppMonthBillService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordAppMonthBillVO openplatformOpenapiRecordAppMonthBillVO = OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.openplatformOpenapiRecordAppMonthBillDOToOpenplatformOpenapiRecordAppMonthBillVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppMonthBillVO);
	}
	/**
	 * 执行 开放平台应用月账单 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiRecordAppMonthBillDO byId = iOpenplatformOpenapiRecordAppMonthBillService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiRecordAppMonthBillVO openplatformOpenapiRecordAppMonthBillVO = OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.openplatformOpenapiRecordAppMonthBillDOToOpenplatformOpenapiRecordAppMonthBillVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordAppMonthBillVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiRecordAppMonthBillService(IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService) {
		this.iOpenplatformOpenapiRecordAppMonthBillService = iOpenplatformOpenapiRecordAppMonthBillService;
	}
}

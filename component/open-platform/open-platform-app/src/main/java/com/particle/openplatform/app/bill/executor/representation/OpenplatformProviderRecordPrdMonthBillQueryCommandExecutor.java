package com.particle.openplatform.app.bill.executor.representation;

import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformProviderRecordPrdMonthBillDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdMonthBillService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformProviderRecordPrdMonthBillPageQueryCommand;
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
 * 开放平台供应商月账单 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdMonthBillQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService;

	/**
	 * 执行 开放平台供应商月账单 列表查询指令
	 * @param openplatformProviderRecordPrdMonthBillQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformProviderRecordPrdMonthBillVO> execute(@Valid OpenplatformProviderRecordPrdMonthBillQueryListCommand openplatformProviderRecordPrdMonthBillQueryListCommand) {
		List<OpenplatformProviderRecordPrdMonthBillDO> openplatformProviderRecordPrdMonthBillDO = iOpenplatformProviderRecordPrdMonthBillService.list(openplatformProviderRecordPrdMonthBillQueryListCommand);
		List<OpenplatformProviderRecordPrdMonthBillVO> openplatformProviderRecordPrdMonthBillVOs = OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.openplatformProviderRecordPrdMonthBillDOsToOpenplatformProviderRecordPrdMonthBillVOs(openplatformProviderRecordPrdMonthBillDO);
		return MultiResponse.of(openplatformProviderRecordPrdMonthBillVOs);
	}
	/**
	 * 执行 开放平台供应商月账单 分页查询指令
	 * @param openplatformProviderRecordPrdMonthBillPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformProviderRecordPrdMonthBillVO> execute(@Valid OpenplatformProviderRecordPrdMonthBillPageQueryCommand openplatformProviderRecordPrdMonthBillPageQueryCommand) {
		Page<OpenplatformProviderRecordPrdMonthBillDO> page = iOpenplatformProviderRecordPrdMonthBillService.listPage(openplatformProviderRecordPrdMonthBillPageQueryCommand);
		return OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台供应商月账单 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> executeDetail(IdCommand detailCommand) {
		OpenplatformProviderRecordPrdMonthBillDO byId = iOpenplatformProviderRecordPrdMonthBillService.getById(detailCommand.getId());
		OpenplatformProviderRecordPrdMonthBillVO openplatformProviderRecordPrdMonthBillVO = OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.openplatformProviderRecordPrdMonthBillDOToOpenplatformProviderRecordPrdMonthBillVO(byId);
		return SingleResponse.of(openplatformProviderRecordPrdMonthBillVO);
	}
	/**
	 * 执行 开放平台供应商月账单 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformProviderRecordPrdMonthBillDO byId = iOpenplatformProviderRecordPrdMonthBillService.getById(detailForUpdateCommand.getId());
		OpenplatformProviderRecordPrdMonthBillVO openplatformProviderRecordPrdMonthBillVO = OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.openplatformProviderRecordPrdMonthBillDOToOpenplatformProviderRecordPrdMonthBillVO(byId);
		return SingleResponse.of(openplatformProviderRecordPrdMonthBillVO);
	}


	@Autowired
	public void setIOpenplatformProviderRecordPrdMonthBillService(IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService) {
		this.iOpenplatformProviderRecordPrdMonthBillService = iOpenplatformProviderRecordPrdMonthBillService;
	}
}

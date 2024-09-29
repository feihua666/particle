package com.particle.openplatform.app.bill.executor.representation;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordCustomerMonthBillService;
import com.particle.openplatform.client.bill.dto.command.representation.OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand;
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
 * 开放平台客户月账单 列表查询指令执行器
 * </p>
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordCustomerMonthBillQueryCommandExecutor  extends AbstractBaseQueryExecutor {

	private IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService;

	/**
	 * 执行 开放平台客户月账单 列表查询指令
	 * @param openplatformOpenapiRecordCustomerMonthBillQueryListCommand
	 * @return
	 */
	public MultiResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> execute(@Valid OpenplatformOpenapiRecordCustomerMonthBillQueryListCommand openplatformOpenapiRecordCustomerMonthBillQueryListCommand) {
		List<OpenplatformOpenapiRecordCustomerMonthBillDO> openplatformOpenapiRecordCustomerMonthBillDO = iOpenplatformOpenapiRecordCustomerMonthBillService.list(openplatformOpenapiRecordCustomerMonthBillQueryListCommand);
		List<OpenplatformOpenapiRecordCustomerMonthBillVO> openplatformOpenapiRecordCustomerMonthBillVOs = OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.openplatformOpenapiRecordCustomerMonthBillDOsToOpenplatformOpenapiRecordCustomerMonthBillVOs(openplatformOpenapiRecordCustomerMonthBillDO);
		return MultiResponse.of(openplatformOpenapiRecordCustomerMonthBillVOs);
	}
	/**
	 * 执行 开放平台客户月账单 分页查询指令
	 * @param openplatformOpenapiRecordCustomerMonthBillPageQueryCommand
	 * @return
	 */
	public PageResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> execute(@Valid OpenplatformOpenapiRecordCustomerMonthBillPageQueryCommand openplatformOpenapiRecordCustomerMonthBillPageQueryCommand) {
		Page<OpenplatformOpenapiRecordCustomerMonthBillDO> page = iOpenplatformOpenapiRecordCustomerMonthBillService.listPage(openplatformOpenapiRecordCustomerMonthBillPageQueryCommand);
		return OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.infrastructurePageToPageResponse(page);
	}

	/**
	 * 执行 开放平台客户月账单 展示用详情查询指令
	 * @param detailCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> executeDetail(IdCommand detailCommand) {
		OpenplatformOpenapiRecordCustomerMonthBillDO byId = iOpenplatformOpenapiRecordCustomerMonthBillService.getById(detailCommand.getId());
		OpenplatformOpenapiRecordCustomerMonthBillVO openplatformOpenapiRecordCustomerMonthBillVO = OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.openplatformOpenapiRecordCustomerMonthBillDOToOpenplatformOpenapiRecordCustomerMonthBillVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordCustomerMonthBillVO);
	}
	/**
	 * 执行 开放平台客户月账单 更新用详情查询指令
	 * @param detailForUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> executeDetailForUpdate(IdCommand detailForUpdateCommand) {
		OpenplatformOpenapiRecordCustomerMonthBillDO byId = iOpenplatformOpenapiRecordCustomerMonthBillService.getById(detailForUpdateCommand.getId());
		OpenplatformOpenapiRecordCustomerMonthBillVO openplatformOpenapiRecordCustomerMonthBillVO = OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.openplatformOpenapiRecordCustomerMonthBillDOToOpenplatformOpenapiRecordCustomerMonthBillVO(byId);
		return SingleResponse.of(openplatformOpenapiRecordCustomerMonthBillVO);
	}


	@Autowired
	public void setIOpenplatformOpenapiRecordCustomerMonthBillService(IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService) {
		this.iOpenplatformOpenapiRecordCustomerMonthBillService = iOpenplatformOpenapiRecordCustomerMonthBillService;
	}
}

package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordCustomerMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordCustomerMonthBillService;
import com.particle.openplatform.infrastructure.bill.dos.OpenplatformOpenapiRecordCustomerMonthBillDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import com.particle.global.dto.response.Response;
import javax.validation.Valid;

/**
 * <p>
 * 开放平台客户月账单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordCustomerMonthBillDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway;
	private IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService;

	/**
	 * 执行 开放平台客户月账单 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordCustomerMonthBillId openplatformOpenapiRecordCustomerMonthBillId = OpenplatformOpenapiRecordCustomerMonthBillId.of(deleteCommand.getId());
		OpenplatformOpenapiRecordCustomerMonthBill byId = openplatformOpenapiRecordCustomerMonthBillGateway.getById(openplatformOpenapiRecordCustomerMonthBillId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordCustomerMonthBillGateway.delete(openplatformOpenapiRecordCustomerMonthBillId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.toOpenplatformOpenapiRecordCustomerMonthBillVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordCustomerMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordCustomerMonthBillGateway(OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway) {
		this.openplatformOpenapiRecordCustomerMonthBillGateway = openplatformOpenapiRecordCustomerMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordCustomerMonthBillService(IOpenplatformOpenapiRecordCustomerMonthBillService iOpenplatformOpenapiRecordCustomerMonthBillService) {
		this.iOpenplatformOpenapiRecordCustomerMonthBillService = iOpenplatformOpenapiRecordCustomerMonthBillService;
	}
}

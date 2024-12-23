package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformOpenapiRecordAppMonthBillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台应用月账单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppMonthBillDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway;
	private IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService;

	/**
	 * 执行 开放平台应用月账单 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformOpenapiRecordAppMonthBillId openplatformOpenapiRecordAppMonthBillId = OpenplatformOpenapiRecordAppMonthBillId.of(deleteCommand.getId());
		OpenplatformOpenapiRecordAppMonthBill byId = openplatformOpenapiRecordAppMonthBillGateway.getById(openplatformOpenapiRecordAppMonthBillId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformOpenapiRecordAppMonthBillGateway.delete(openplatformOpenapiRecordAppMonthBillId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.toOpenplatformOpenapiRecordAppMonthBillVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppMonthBillGateway(OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway) {
		this.openplatformOpenapiRecordAppMonthBillGateway = openplatformOpenapiRecordAppMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformOpenapiRecordAppMonthBillService(IOpenplatformOpenapiRecordAppMonthBillService iOpenplatformOpenapiRecordAppMonthBillService) {
		this.iOpenplatformOpenapiRecordAppMonthBillService = iOpenplatformOpenapiRecordAppMonthBillService;
	}
}

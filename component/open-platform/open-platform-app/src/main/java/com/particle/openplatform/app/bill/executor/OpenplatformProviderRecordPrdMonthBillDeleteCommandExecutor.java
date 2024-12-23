package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.common.client.dto.command.IdCommand;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.Assert;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdMonthBillGateway;
import com.particle.openplatform.infrastructure.bill.service.IOpenplatformProviderRecordPrdMonthBillService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

/**
 * <p>
 * 开放平台供应商月账单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdMonthBillDeleteCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway;
	private IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService;

	/**
	 * 执行 开放平台供应商月账单 删除指令
	 * @param deleteCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> execute(@Valid IdCommand deleteCommand) {
		OpenplatformProviderRecordPrdMonthBillId openplatformProviderRecordPrdMonthBillId = OpenplatformProviderRecordPrdMonthBillId.of(deleteCommand.getId());
		OpenplatformProviderRecordPrdMonthBill byId = openplatformProviderRecordPrdMonthBillGateway.getById(openplatformProviderRecordPrdMonthBillId);
		Assert.notNull(byId,ErrorCodeGlobalEnum.DATA_NOT_FOUND);
		boolean delete = openplatformProviderRecordPrdMonthBillGateway.delete(openplatformProviderRecordPrdMonthBillId,deleteCommand);
		if (delete) {
			return SingleResponse.of(OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.toOpenplatformProviderRecordPrdMonthBillVO(byId));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.DELETE_ERROR);
	}


	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdMonthBillGateway(OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway) {
		this.openplatformProviderRecordPrdMonthBillGateway = openplatformProviderRecordPrdMonthBillGateway;
	}
	@Autowired
	public void setIOpenplatformProviderRecordPrdMonthBillService(IOpenplatformProviderRecordPrdMonthBillService iOpenplatformProviderRecordPrdMonthBillService) {
		this.iOpenplatformProviderRecordPrdMonthBillService = iOpenplatformProviderRecordPrdMonthBillService;
	}
}

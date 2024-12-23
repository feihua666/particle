package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBill;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordCustomerMonthBillGateway;
import jakarta.validation.Valid;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

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
public class OpenplatformOpenapiRecordCustomerMonthBillCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway;

	/**
	 * 执行开放平台客户月账单添加指令
	 * @param openplatformOpenapiRecordCustomerMonthBillCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> execute(@Valid OpenplatformOpenapiRecordCustomerMonthBillCreateCommand openplatformOpenapiRecordCustomerMonthBillCreateCommand) {
		OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill = createByOpenplatformOpenapiRecordCustomerMonthBillCreateCommand(openplatformOpenapiRecordCustomerMonthBillCreateCommand);
		openplatformOpenapiRecordCustomerMonthBill.setAddControl(openplatformOpenapiRecordCustomerMonthBillCreateCommand);
		boolean save = openplatformOpenapiRecordCustomerMonthBillGateway.save(openplatformOpenapiRecordCustomerMonthBill);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.toOpenplatformOpenapiRecordCustomerMonthBillVO(openplatformOpenapiRecordCustomerMonthBill));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台客户月账单创建指令创建开放平台客户月账单模型
	 * @param openplatformOpenapiRecordCustomerMonthBillCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordCustomerMonthBill createByOpenplatformOpenapiRecordCustomerMonthBillCreateCommand(OpenplatformOpenapiRecordCustomerMonthBillCreateCommand openplatformOpenapiRecordCustomerMonthBillCreateCommand){
		OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill = OpenplatformOpenapiRecordCustomerMonthBill.create();
		OpenplatformOpenapiRecordCustomerMonthBillCreateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping.instance.fillOpenplatformOpenapiRecordCustomerMonthBillByOpenplatformOpenapiRecordCustomerMonthBillCreateCommand(openplatformOpenapiRecordCustomerMonthBill, openplatformOpenapiRecordCustomerMonthBillCreateCommand);
		return openplatformOpenapiRecordCustomerMonthBill;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformOpenapiRecordCustomerMonthBillCreateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping{
		OpenplatformOpenapiRecordCustomerMonthBillCreateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordCustomerMonthBillCreateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordCustomerMonthBill
		 * @param openplatformOpenapiRecordCustomerMonthBillCreateCommand
		 */
		void fillOpenplatformOpenapiRecordCustomerMonthBillByOpenplatformOpenapiRecordCustomerMonthBillCreateCommand(@MappingTarget OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill, OpenplatformOpenapiRecordCustomerMonthBillCreateCommand openplatformOpenapiRecordCustomerMonthBillCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordCustomerMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordCustomerMonthBillGateway(OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway) {
		this.openplatformOpenapiRecordCustomerMonthBillGateway = openplatformOpenapiRecordCustomerMonthBillGateway;
	}
}

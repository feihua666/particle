package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordCustomerMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordCustomerMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordCustomerMonthBillGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 开放平台客户月账单 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordCustomerMonthBillGateway openplatformOpenapiRecordCustomerMonthBillGateway;

	/**
	 * 执行 开放平台客户月账单 更新指令
	 * @param openplatformOpenapiRecordCustomerMonthBillUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordCustomerMonthBillVO> execute(@Valid OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand openplatformOpenapiRecordCustomerMonthBillUpdateCommand) {
		OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill = createByOpenplatformOpenapiRecordCustomerMonthBillUpdateCommand(openplatformOpenapiRecordCustomerMonthBillUpdateCommand);
		openplatformOpenapiRecordCustomerMonthBill.setUpdateControl(openplatformOpenapiRecordCustomerMonthBillUpdateCommand);
		boolean save = openplatformOpenapiRecordCustomerMonthBillGateway.save(openplatformOpenapiRecordCustomerMonthBill);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordCustomerMonthBillAppStructMapping.instance.toOpenplatformOpenapiRecordCustomerMonthBillVO(openplatformOpenapiRecordCustomerMonthBill));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台客户月账单更新指令创建开放平台客户月账单模型
	 * @param openplatformOpenapiRecordCustomerMonthBillUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordCustomerMonthBill createByOpenplatformOpenapiRecordCustomerMonthBillUpdateCommand(OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand openplatformOpenapiRecordCustomerMonthBillUpdateCommand){
		OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill = OpenplatformOpenapiRecordCustomerMonthBill.create();
		OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping.instance.fillOpenplatformOpenapiRecordCustomerMonthBillByOpenplatformOpenapiRecordCustomerMonthBillUpdateCommand(openplatformOpenapiRecordCustomerMonthBill, openplatformOpenapiRecordCustomerMonthBillUpdateCommand);
		return openplatformOpenapiRecordCustomerMonthBill;
	}

	@Mapper
	interface OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping{
		OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping instance = Mappers.getMapper(OpenplatformOpenapiRecordCustomerMonthBillUpdateCommandToOpenplatformOpenapiRecordCustomerMonthBillMapping.class );

		default OpenplatformOpenapiRecordCustomerMonthBillId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiRecordCustomerMonthBillId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordCustomerMonthBill
		 * @param openplatformOpenapiRecordCustomerMonthBillUpdateCommand
		 */
		void fillOpenplatformOpenapiRecordCustomerMonthBillByOpenplatformOpenapiRecordCustomerMonthBillUpdateCommand(@MappingTarget OpenplatformOpenapiRecordCustomerMonthBill openplatformOpenapiRecordCustomerMonthBill, OpenplatformOpenapiRecordCustomerMonthBillUpdateCommand openplatformOpenapiRecordCustomerMonthBillUpdateCommand);
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

package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBill;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppMonthBillGateway;
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
 * 开放平台应用月账单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-10-12 09:47:54
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppMonthBillCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway;

	/**
	 * 执行开放平台应用月账单添加指令
	 * @param openplatformOpenapiRecordAppMonthBillCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> execute(@Valid OpenplatformOpenapiRecordAppMonthBillCreateCommand openplatformOpenapiRecordAppMonthBillCreateCommand) {
		OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill = createByOpenplatformOpenapiRecordAppMonthBillCreateCommand(openplatformOpenapiRecordAppMonthBillCreateCommand);
		openplatformOpenapiRecordAppMonthBill.setAddControl(openplatformOpenapiRecordAppMonthBillCreateCommand);
		boolean save = openplatformOpenapiRecordAppMonthBillGateway.save(openplatformOpenapiRecordAppMonthBill);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.toOpenplatformOpenapiRecordAppMonthBillVO(openplatformOpenapiRecordAppMonthBill));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用月账单创建指令创建开放平台应用月账单模型
	 * @param openplatformOpenapiRecordAppMonthBillCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppMonthBill createByOpenplatformOpenapiRecordAppMonthBillCreateCommand(OpenplatformOpenapiRecordAppMonthBillCreateCommand openplatformOpenapiRecordAppMonthBillCreateCommand){
		OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill = OpenplatformOpenapiRecordAppMonthBill.create();
		OpenplatformOpenapiRecordAppMonthBillCreateCommandToOpenplatformOpenapiRecordAppMonthBillMapping.instance.fillOpenplatformOpenapiRecordAppMonthBillByOpenplatformOpenapiRecordAppMonthBillCreateCommand(openplatformOpenapiRecordAppMonthBill, openplatformOpenapiRecordAppMonthBillCreateCommand);
		return openplatformOpenapiRecordAppMonthBill;
	}

	@Mapper
	interface  OpenplatformOpenapiRecordAppMonthBillCreateCommandToOpenplatformOpenapiRecordAppMonthBillMapping{
		OpenplatformOpenapiRecordAppMonthBillCreateCommandToOpenplatformOpenapiRecordAppMonthBillMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppMonthBillCreateCommandToOpenplatformOpenapiRecordAppMonthBillMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppMonthBill
		 * @param openplatformOpenapiRecordAppMonthBillCreateCommand
		 */
		void fillOpenplatformOpenapiRecordAppMonthBillByOpenplatformOpenapiRecordAppMonthBillCreateCommand(@MappingTarget OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill, OpenplatformOpenapiRecordAppMonthBillCreateCommand openplatformOpenapiRecordAppMonthBillCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppMonthBillGateway(OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway) {
		this.openplatformOpenapiRecordAppMonthBillGateway = openplatformOpenapiRecordAppMonthBillGateway;
	}
}

package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBill;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdMonthBillGateway;
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
 * 开放平台供应商月账单 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:53
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdMonthBillCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway;

	/**
	 * 执行开放平台供应商月账单添加指令
	 * @param openplatformProviderRecordPrdMonthBillCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> execute(@Valid OpenplatformProviderRecordPrdMonthBillCreateCommand openplatformProviderRecordPrdMonthBillCreateCommand) {
		OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill = createByOpenplatformProviderRecordPrdMonthBillCreateCommand(openplatformProviderRecordPrdMonthBillCreateCommand);
		openplatformProviderRecordPrdMonthBill.setAddControl(openplatformProviderRecordPrdMonthBillCreateCommand);
		boolean save = openplatformProviderRecordPrdMonthBillGateway.save(openplatformProviderRecordPrdMonthBill);
		if (save) {
			return SingleResponse.of(OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.toOpenplatformProviderRecordPrdMonthBillVO(openplatformProviderRecordPrdMonthBill));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商月账单创建指令创建开放平台供应商月账单模型
	 * @param openplatformProviderRecordPrdMonthBillCreateCommand
	 * @return
	 */
	private OpenplatformProviderRecordPrdMonthBill createByOpenplatformProviderRecordPrdMonthBillCreateCommand(OpenplatformProviderRecordPrdMonthBillCreateCommand openplatformProviderRecordPrdMonthBillCreateCommand){
		OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill = OpenplatformProviderRecordPrdMonthBill.create();
		OpenplatformProviderRecordPrdMonthBillCreateCommandToOpenplatformProviderRecordPrdMonthBillMapping.instance.fillOpenplatformProviderRecordPrdMonthBillByOpenplatformProviderRecordPrdMonthBillCreateCommand(openplatformProviderRecordPrdMonthBill, openplatformProviderRecordPrdMonthBillCreateCommand);
		return openplatformProviderRecordPrdMonthBill;
	}

	@Mapper
	interface  OpenplatformProviderRecordPrdMonthBillCreateCommandToOpenplatformProviderRecordPrdMonthBillMapping{
		OpenplatformProviderRecordPrdMonthBillCreateCommandToOpenplatformProviderRecordPrdMonthBillMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdMonthBillCreateCommandToOpenplatformProviderRecordPrdMonthBillMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderRecordPrdMonthBill
		 * @param openplatformProviderRecordPrdMonthBillCreateCommand
		 */
		void fillOpenplatformProviderRecordPrdMonthBillByOpenplatformProviderRecordPrdMonthBillCreateCommand(@MappingTarget OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill, OpenplatformProviderRecordPrdMonthBillCreateCommand openplatformProviderRecordPrdMonthBillCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdMonthBillGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdMonthBillGateway(OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway) {
		this.openplatformProviderRecordPrdMonthBillGateway = openplatformProviderRecordPrdMonthBillGateway;
	}
}

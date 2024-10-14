package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppMonthBillId;
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
 * 开放平台应用月账单 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppMonthBillUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppMonthBillGateway openplatformOpenapiRecordAppMonthBillGateway;

	/**
	 * 执行 开放平台应用月账单 更新指令
	 * @param openplatformOpenapiRecordAppMonthBillUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppMonthBillVO> execute(@Valid OpenplatformOpenapiRecordAppMonthBillUpdateCommand openplatformOpenapiRecordAppMonthBillUpdateCommand) {
		OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill = createByOpenplatformOpenapiRecordAppMonthBillUpdateCommand(openplatformOpenapiRecordAppMonthBillUpdateCommand);
		openplatformOpenapiRecordAppMonthBill.setUpdateControl(openplatformOpenapiRecordAppMonthBillUpdateCommand);
		boolean save = openplatformOpenapiRecordAppMonthBillGateway.save(openplatformOpenapiRecordAppMonthBill);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppMonthBillAppStructMapping.instance.toOpenplatformOpenapiRecordAppMonthBillVO(openplatformOpenapiRecordAppMonthBill));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用月账单更新指令创建开放平台应用月账单模型
	 * @param openplatformOpenapiRecordAppMonthBillUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppMonthBill createByOpenplatformOpenapiRecordAppMonthBillUpdateCommand(OpenplatformOpenapiRecordAppMonthBillUpdateCommand openplatformOpenapiRecordAppMonthBillUpdateCommand){
		OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill = OpenplatformOpenapiRecordAppMonthBill.create();
		OpenplatformOpenapiRecordAppMonthBillUpdateCommandToOpenplatformOpenapiRecordAppMonthBillMapping.instance.fillOpenplatformOpenapiRecordAppMonthBillByOpenplatformOpenapiRecordAppMonthBillUpdateCommand(openplatformOpenapiRecordAppMonthBill, openplatformOpenapiRecordAppMonthBillUpdateCommand);
		return openplatformOpenapiRecordAppMonthBill;
	}

	@Mapper
	interface OpenplatformOpenapiRecordAppMonthBillUpdateCommandToOpenplatformOpenapiRecordAppMonthBillMapping{
		OpenplatformOpenapiRecordAppMonthBillUpdateCommandToOpenplatformOpenapiRecordAppMonthBillMapping instance = Mappers.getMapper(OpenplatformOpenapiRecordAppMonthBillUpdateCommandToOpenplatformOpenapiRecordAppMonthBillMapping.class );

		default OpenplatformOpenapiRecordAppMonthBillId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiRecordAppMonthBillId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppMonthBill
		 * @param openplatformOpenapiRecordAppMonthBillUpdateCommand
		 */
		void fillOpenplatformOpenapiRecordAppMonthBillByOpenplatformOpenapiRecordAppMonthBillUpdateCommand(@MappingTarget OpenplatformOpenapiRecordAppMonthBill openplatformOpenapiRecordAppMonthBill, OpenplatformOpenapiRecordAppMonthBillUpdateCommand openplatformOpenapiRecordAppMonthBillUpdateCommand);
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

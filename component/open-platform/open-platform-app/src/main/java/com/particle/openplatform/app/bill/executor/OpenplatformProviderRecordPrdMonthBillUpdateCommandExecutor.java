package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdMonthBillAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdMonthBillUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdMonthBillVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBill;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdMonthBillId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdMonthBillGateway;
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
 * 开放平台供应商月账单 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdMonthBillUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdMonthBillGateway openplatformProviderRecordPrdMonthBillGateway;

	/**
	 * 执行 开放平台供应商月账单 更新指令
	 * @param openplatformProviderRecordPrdMonthBillUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdMonthBillVO> execute(@Valid OpenplatformProviderRecordPrdMonthBillUpdateCommand openplatformProviderRecordPrdMonthBillUpdateCommand) {
		OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill = createByOpenplatformProviderRecordPrdMonthBillUpdateCommand(openplatformProviderRecordPrdMonthBillUpdateCommand);
		openplatformProviderRecordPrdMonthBill.setUpdateControl(openplatformProviderRecordPrdMonthBillUpdateCommand);
		boolean save = openplatformProviderRecordPrdMonthBillGateway.save(openplatformProviderRecordPrdMonthBill);
		if (save) {
			return SingleResponse.of(OpenplatformProviderRecordPrdMonthBillAppStructMapping.instance.toOpenplatformProviderRecordPrdMonthBillVO(openplatformProviderRecordPrdMonthBill));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商月账单更新指令创建开放平台供应商月账单模型
	 * @param openplatformProviderRecordPrdMonthBillUpdateCommand
	 * @return
	 */
	private OpenplatformProviderRecordPrdMonthBill createByOpenplatformProviderRecordPrdMonthBillUpdateCommand(OpenplatformProviderRecordPrdMonthBillUpdateCommand openplatformProviderRecordPrdMonthBillUpdateCommand){
		OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill = OpenplatformProviderRecordPrdMonthBill.create();
		OpenplatformProviderRecordPrdMonthBillUpdateCommandToOpenplatformProviderRecordPrdMonthBillMapping.instance.fillOpenplatformProviderRecordPrdMonthBillByOpenplatformProviderRecordPrdMonthBillUpdateCommand(openplatformProviderRecordPrdMonthBill, openplatformProviderRecordPrdMonthBillUpdateCommand);
		return openplatformProviderRecordPrdMonthBill;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformProviderRecordPrdMonthBillUpdateCommandToOpenplatformProviderRecordPrdMonthBillMapping{
		OpenplatformProviderRecordPrdMonthBillUpdateCommandToOpenplatformProviderRecordPrdMonthBillMapping instance = Mappers.getMapper(OpenplatformProviderRecordPrdMonthBillUpdateCommandToOpenplatformProviderRecordPrdMonthBillMapping.class );

		default OpenplatformProviderRecordPrdMonthBillId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformProviderRecordPrdMonthBillId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderRecordPrdMonthBill
		 * @param openplatformProviderRecordPrdMonthBillUpdateCommand
		 */
		void fillOpenplatformProviderRecordPrdMonthBillByOpenplatformProviderRecordPrdMonthBillUpdateCommand(@MappingTarget OpenplatformProviderRecordPrdMonthBill openplatformProviderRecordPrdMonthBill, OpenplatformProviderRecordPrdMonthBillUpdateCommand openplatformProviderRecordPrdMonthBillUpdateCommand);
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

package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummary;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
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
 * 开放平台应用开放接口月汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:43
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;

	/**
	 * 执行开放平台应用开放接口月汇总添加指令
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand) {
		OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary = createByOpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand(openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);
		openplatformOpenapiRecordAppOpenapiMonthSummary.setAddControl(openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);
		boolean save = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway.save(openplatformOpenapiRecordAppOpenapiMonthSummary);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(openplatformOpenapiRecordAppOpenapiMonthSummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用开放接口月汇总创建指令创建开放平台应用开放接口月汇总模型
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppOpenapiMonthSummary createByOpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand(OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand){
		OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary = OpenplatformOpenapiRecordAppOpenapiMonthSummary.create();
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping.instance.fillOpenplatformOpenapiRecordAppOpenapiMonthSummaryByOpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand(openplatformOpenapiRecordAppOpenapiMonthSummary, openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);
		return openplatformOpenapiRecordAppOpenapiMonthSummary;
	}

	@Mapper
	interface  OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping{
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppOpenapiMonthSummary
		 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand
		 */
		void fillOpenplatformOpenapiRecordAppOpenapiMonthSummaryByOpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand(@MappingTarget OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary, OpenplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway(OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiMonthSummaryGateway = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;
	}
}
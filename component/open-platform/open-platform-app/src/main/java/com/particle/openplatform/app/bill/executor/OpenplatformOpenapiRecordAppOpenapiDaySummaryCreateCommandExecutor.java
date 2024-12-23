package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummary;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway;
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
 * 开放平台应用开放接口日汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:51:02
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway;

	/**
	 * 执行开放平台应用开放接口日汇总添加指令
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand) {
		OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary = createByOpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand(openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);
		openplatformOpenapiRecordAppOpenapiDaySummary.setAddControl(openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);
		boolean save = openplatformOpenapiRecordAppOpenapiDaySummaryGateway.save(openplatformOpenapiRecordAppOpenapiDaySummary);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(openplatformOpenapiRecordAppOpenapiDaySummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用开放接口日汇总创建指令创建开放平台应用开放接口日汇总模型
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppOpenapiDaySummary createByOpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand(OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand){
		OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary = OpenplatformOpenapiRecordAppOpenapiDaySummary.create();
		OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping.instance.fillOpenplatformOpenapiRecordAppOpenapiDaySummaryByOpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand(openplatformOpenapiRecordAppOpenapiDaySummary, openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);
		return openplatformOpenapiRecordAppOpenapiDaySummary;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping{
		OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping instance = Mappers.getMapper( OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppOpenapiDaySummary
		 * @param openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand
		 */
		void fillOpenplatformOpenapiRecordAppOpenapiDaySummaryByOpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand(@MappingTarget OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary, OpenplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand openplatformOpenapiRecordAppOpenapiDaySummaryCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDaySummaryGateway(OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDaySummaryGateway = openplatformOpenapiRecordAppOpenapiDaySummaryGateway;
	}
}

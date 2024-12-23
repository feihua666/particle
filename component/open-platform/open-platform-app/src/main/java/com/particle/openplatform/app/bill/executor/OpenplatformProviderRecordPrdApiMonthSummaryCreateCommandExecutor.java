package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummary;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiMonthSummaryGateway;
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
 * 开放平台供应商接口月汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:34
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway;

	/**
	 * 执行开放平台供应商接口月汇总添加指令
	 * @param openplatformProviderRecordPrdApiMonthSummaryCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand openplatformProviderRecordPrdApiMonthSummaryCreateCommand) {
		OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary = createByOpenplatformProviderRecordPrdApiMonthSummaryCreateCommand(openplatformProviderRecordPrdApiMonthSummaryCreateCommand);
		openplatformProviderRecordPrdApiMonthSummary.setAddControl(openplatformProviderRecordPrdApiMonthSummaryCreateCommand);
		boolean save = openplatformProviderRecordPrdApiMonthSummaryGateway.save(openplatformProviderRecordPrdApiMonthSummary);
		if (save) {
			return SingleResponse.of(OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.toOpenplatformProviderRecordPrdApiMonthSummaryVO(openplatformProviderRecordPrdApiMonthSummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商接口月汇总创建指令创建开放平台供应商接口月汇总模型
	 * @param openplatformProviderRecordPrdApiMonthSummaryCreateCommand
	 * @return
	 */
	private OpenplatformProviderRecordPrdApiMonthSummary createByOpenplatformProviderRecordPrdApiMonthSummaryCreateCommand(OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand openplatformProviderRecordPrdApiMonthSummaryCreateCommand){
		OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary = OpenplatformProviderRecordPrdApiMonthSummary.create();
		OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping.instance.fillOpenplatformProviderRecordPrdApiMonthSummaryByOpenplatformProviderRecordPrdApiMonthSummaryCreateCommand(openplatformProviderRecordPrdApiMonthSummary, openplatformProviderRecordPrdApiMonthSummaryCreateCommand);
		return openplatformProviderRecordPrdApiMonthSummary;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping{
		OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdApiMonthSummaryCreateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderRecordPrdApiMonthSummary
		 * @param openplatformProviderRecordPrdApiMonthSummaryCreateCommand
		 */
		void fillOpenplatformProviderRecordPrdApiMonthSummaryByOpenplatformProviderRecordPrdApiMonthSummaryCreateCommand(@MappingTarget OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary, OpenplatformProviderRecordPrdApiMonthSummaryCreateCommand openplatformProviderRecordPrdApiMonthSummaryCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdApiMonthSummaryGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdApiMonthSummaryGateway(OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway) {
		this.openplatformProviderRecordPrdApiMonthSummaryGateway = openplatformProviderRecordPrdApiMonthSummaryGateway;
	}
}

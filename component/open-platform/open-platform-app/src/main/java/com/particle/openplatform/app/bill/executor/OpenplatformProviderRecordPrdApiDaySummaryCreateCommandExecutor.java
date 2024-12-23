package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryCreateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummary;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiDaySummaryGateway;
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
 * 开放平台供应商接口日汇总 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-12 10:52:17
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiDaySummaryCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway;

	/**
	 * 执行开放平台供应商接口日汇总添加指令
	 * @param openplatformProviderRecordPrdApiDaySummaryCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiDaySummaryCreateCommand openplatformProviderRecordPrdApiDaySummaryCreateCommand) {
		OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary = createByOpenplatformProviderRecordPrdApiDaySummaryCreateCommand(openplatformProviderRecordPrdApiDaySummaryCreateCommand);
		openplatformProviderRecordPrdApiDaySummary.setAddControl(openplatformProviderRecordPrdApiDaySummaryCreateCommand);
		boolean save = openplatformProviderRecordPrdApiDaySummaryGateway.save(openplatformProviderRecordPrdApiDaySummary);
		if (save) {
			return SingleResponse.of(OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.toOpenplatformProviderRecordPrdApiDaySummaryVO(openplatformProviderRecordPrdApiDaySummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商接口日汇总创建指令创建开放平台供应商接口日汇总模型
	 * @param openplatformProviderRecordPrdApiDaySummaryCreateCommand
	 * @return
	 */
	private OpenplatformProviderRecordPrdApiDaySummary createByOpenplatformProviderRecordPrdApiDaySummaryCreateCommand(OpenplatformProviderRecordPrdApiDaySummaryCreateCommand openplatformProviderRecordPrdApiDaySummaryCreateCommand){
		OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary = OpenplatformProviderRecordPrdApiDaySummary.create();
		OpenplatformProviderRecordPrdApiDaySummaryCreateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping.instance.fillOpenplatformProviderRecordPrdApiDaySummaryByOpenplatformProviderRecordPrdApiDaySummaryCreateCommand(openplatformProviderRecordPrdApiDaySummary, openplatformProviderRecordPrdApiDaySummaryCreateCommand);
		return openplatformProviderRecordPrdApiDaySummary;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  OpenplatformProviderRecordPrdApiDaySummaryCreateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping{
		OpenplatformProviderRecordPrdApiDaySummaryCreateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping instance = Mappers.getMapper( OpenplatformProviderRecordPrdApiDaySummaryCreateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderRecordPrdApiDaySummary
		 * @param openplatformProviderRecordPrdApiDaySummaryCreateCommand
		 */
		void fillOpenplatformProviderRecordPrdApiDaySummaryByOpenplatformProviderRecordPrdApiDaySummaryCreateCommand(@MappingTarget OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary, OpenplatformProviderRecordPrdApiDaySummaryCreateCommand openplatformProviderRecordPrdApiDaySummaryCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformProviderRecordPrdApiDaySummaryGateway
	 */
	@Autowired
	public void setOpenplatformProviderRecordPrdApiDaySummaryGateway(OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway) {
		this.openplatformProviderRecordPrdApiDaySummaryGateway = openplatformProviderRecordPrdApiDaySummaryGateway;
	}
}

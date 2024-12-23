package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiDaySummaryId;
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
 * 开放平台供应商接口日汇总 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiDaySummaryGateway openplatformProviderRecordPrdApiDaySummaryGateway;

	/**
	 * 执行 开放平台供应商接口日汇总 更新指令
	 * @param openplatformProviderRecordPrdApiDaySummaryUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiDaySummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand openplatformProviderRecordPrdApiDaySummaryUpdateCommand) {
		OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary = createByOpenplatformProviderRecordPrdApiDaySummaryUpdateCommand(openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
		openplatformProviderRecordPrdApiDaySummary.setUpdateControl(openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
		boolean save = openplatformProviderRecordPrdApiDaySummaryGateway.save(openplatformProviderRecordPrdApiDaySummary);
		if (save) {
			return SingleResponse.of(OpenplatformProviderRecordPrdApiDaySummaryAppStructMapping.instance.toOpenplatformProviderRecordPrdApiDaySummaryVO(openplatformProviderRecordPrdApiDaySummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商接口日汇总更新指令创建开放平台供应商接口日汇总模型
	 * @param openplatformProviderRecordPrdApiDaySummaryUpdateCommand
	 * @return
	 */
	private OpenplatformProviderRecordPrdApiDaySummary createByOpenplatformProviderRecordPrdApiDaySummaryUpdateCommand(OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand openplatformProviderRecordPrdApiDaySummaryUpdateCommand){
		OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary = OpenplatformProviderRecordPrdApiDaySummary.create();
		OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping.instance.fillOpenplatformProviderRecordPrdApiDaySummaryByOpenplatformProviderRecordPrdApiDaySummaryUpdateCommand(openplatformProviderRecordPrdApiDaySummary, openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
		return openplatformProviderRecordPrdApiDaySummary;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping{
		OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping instance = Mappers.getMapper(OpenplatformProviderRecordPrdApiDaySummaryUpdateCommandToOpenplatformProviderRecordPrdApiDaySummaryMapping.class );

		default OpenplatformProviderRecordPrdApiDaySummaryId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformProviderRecordPrdApiDaySummaryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderRecordPrdApiDaySummary
		 * @param openplatformProviderRecordPrdApiDaySummaryUpdateCommand
		 */
		void fillOpenplatformProviderRecordPrdApiDaySummaryByOpenplatformProviderRecordPrdApiDaySummaryUpdateCommand(@MappingTarget OpenplatformProviderRecordPrdApiDaySummary openplatformProviderRecordPrdApiDaySummary, OpenplatformProviderRecordPrdApiDaySummaryUpdateCommand openplatformProviderRecordPrdApiDaySummaryUpdateCommand);
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

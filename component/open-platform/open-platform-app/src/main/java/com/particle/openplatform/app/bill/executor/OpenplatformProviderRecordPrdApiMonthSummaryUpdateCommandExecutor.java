package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformProviderRecordPrdApiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformProviderRecordPrdApiMonthSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformProviderRecordPrdApiMonthSummaryGateway;
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
 * 开放平台供应商接口月汇总 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformProviderRecordPrdApiMonthSummaryGateway openplatformProviderRecordPrdApiMonthSummaryGateway;

	/**
	 * 执行 开放平台供应商接口月汇总 更新指令
	 * @param openplatformProviderRecordPrdApiMonthSummaryUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformProviderRecordPrdApiMonthSummaryVO> execute(@Valid OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand openplatformProviderRecordPrdApiMonthSummaryUpdateCommand) {
		OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary = createByOpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand(openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
		openplatformProviderRecordPrdApiMonthSummary.setUpdateControl(openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
		boolean save = openplatformProviderRecordPrdApiMonthSummaryGateway.save(openplatformProviderRecordPrdApiMonthSummary);
		if (save) {
			return SingleResponse.of(OpenplatformProviderRecordPrdApiMonthSummaryAppStructMapping.instance.toOpenplatformProviderRecordPrdApiMonthSummaryVO(openplatformProviderRecordPrdApiMonthSummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台供应商接口月汇总更新指令创建开放平台供应商接口月汇总模型
	 * @param openplatformProviderRecordPrdApiMonthSummaryUpdateCommand
	 * @return
	 */
	private OpenplatformProviderRecordPrdApiMonthSummary createByOpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand(OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand openplatformProviderRecordPrdApiMonthSummaryUpdateCommand){
		OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary = OpenplatformProviderRecordPrdApiMonthSummary.create();
		OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping.instance.fillOpenplatformProviderRecordPrdApiMonthSummaryByOpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand(openplatformProviderRecordPrdApiMonthSummary, openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
		return openplatformProviderRecordPrdApiMonthSummary;
	}

	@Mapper
	interface OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping{
		OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping instance = Mappers.getMapper(OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommandToOpenplatformProviderRecordPrdApiMonthSummaryMapping.class );

		default OpenplatformProviderRecordPrdApiMonthSummaryId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformProviderRecordPrdApiMonthSummaryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformProviderRecordPrdApiMonthSummary
		 * @param openplatformProviderRecordPrdApiMonthSummaryUpdateCommand
		 */
		void fillOpenplatformProviderRecordPrdApiMonthSummaryByOpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand(@MappingTarget OpenplatformProviderRecordPrdApiMonthSummary openplatformProviderRecordPrdApiMonthSummary, OpenplatformProviderRecordPrdApiMonthSummaryUpdateCommand openplatformProviderRecordPrdApiMonthSummaryUpdateCommand);
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

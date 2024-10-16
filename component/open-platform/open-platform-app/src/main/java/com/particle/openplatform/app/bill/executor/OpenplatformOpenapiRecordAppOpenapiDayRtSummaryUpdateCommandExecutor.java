package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId;
import com.particle.openplatform.domain.bill.gateway.OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
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
 * 开放平台应用开放接口日实时汇总 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;

	/**
	 * 执行 开放平台应用开放接口日实时汇总 更新指令
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand) {
		OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary = createByOpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand(openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
		openplatformOpenapiRecordAppOpenapiDayRtSummary.setUpdateControl(openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
		boolean save = openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway.save(openplatformOpenapiRecordAppOpenapiDayRtSummary);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiDayRtSummaryVO(openplatformOpenapiRecordAppOpenapiDayRtSummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用开放接口日实时汇总更新指令创建开放平台应用开放接口日实时汇总模型
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppOpenapiDayRtSummary createByOpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand){
		OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary = OpenplatformOpenapiRecordAppOpenapiDayRtSummary.create();
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryMapping.instance.fillOpenplatformOpenapiRecordAppOpenapiDayRtSummaryByOpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand(openplatformOpenapiRecordAppOpenapiDayRtSummary, openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
		return openplatformOpenapiRecordAppOpenapiDayRtSummary;
	}

	@Mapper
	interface OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryMapping{
		OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryMapping instance = Mappers.getMapper(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDayRtSummaryMapping.class );

		default OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiRecordAppOpenapiDayRtSummaryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppOpenapiDayRtSummary
		 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand
		 */
		void fillOpenplatformOpenapiRecordAppOpenapiDayRtSummaryByOpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand(@MappingTarget OpenplatformOpenapiRecordAppOpenapiDayRtSummary openplatformOpenapiRecordAppOpenapiDayRtSummary, OpenplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDayRtSummaryUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway(OpenplatformOpenapiRecordAppOpenapiDayRtSummaryGateway openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway) {
		this.openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway = openplatformOpenapiRecordAppOpenapiDayRtSummaryGateway;
	}
}

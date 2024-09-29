package com.particle.openplatform.app.bill.executor;

import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiMonthSummaryId;
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
 * 开放平台应用开放接口月汇总 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiMonthSummaryGateway openplatformOpenapiRecordAppOpenapiMonthSummaryGateway;

	/**
	 * 执行 开放平台应用开放接口月汇总 更新指令
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiMonthSummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand) {
		OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary = createByOpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand(openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
		openplatformOpenapiRecordAppOpenapiMonthSummary.setUpdateControl(openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
		boolean save = openplatformOpenapiRecordAppOpenapiMonthSummaryGateway.save(openplatformOpenapiRecordAppOpenapiMonthSummary);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiMonthSummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiMonthSummaryVO(openplatformOpenapiRecordAppOpenapiMonthSummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用开放接口月汇总更新指令创建开放平台应用开放接口月汇总模型
	 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppOpenapiMonthSummary createByOpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand(OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand){
		OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary = OpenplatformOpenapiRecordAppOpenapiMonthSummary.create();
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping.instance.fillOpenplatformOpenapiRecordAppOpenapiMonthSummaryByOpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand(openplatformOpenapiRecordAppOpenapiMonthSummary, openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
		return openplatformOpenapiRecordAppOpenapiMonthSummary;
	}

	@Mapper
	interface OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping{
		OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping instance = Mappers.getMapper(OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiMonthSummaryMapping.class );

		default OpenplatformOpenapiRecordAppOpenapiMonthSummaryId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiRecordAppOpenapiMonthSummaryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppOpenapiMonthSummary
		 * @param openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand
		 */
		void fillOpenplatformOpenapiRecordAppOpenapiMonthSummaryByOpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand(@MappingTarget OpenplatformOpenapiRecordAppOpenapiMonthSummary openplatformOpenapiRecordAppOpenapiMonthSummary, OpenplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand openplatformOpenapiRecordAppOpenapiMonthSummaryUpdateCommand);
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

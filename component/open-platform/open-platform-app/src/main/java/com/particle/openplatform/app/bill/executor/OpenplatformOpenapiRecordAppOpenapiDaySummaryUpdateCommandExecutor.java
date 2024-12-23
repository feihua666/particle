package com.particle.openplatform.app.bill.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.bill.structmapping.OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping;
import com.particle.openplatform.client.bill.dto.command.OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand;
import com.particle.openplatform.client.bill.dto.data.OpenplatformOpenapiRecordAppOpenapiDaySummaryVO;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummary;
import com.particle.openplatform.domain.bill.OpenplatformOpenapiRecordAppOpenapiDaySummaryId;
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
 * 开放平台应用开放接口日汇总 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiRecordAppOpenapiDaySummaryGateway openplatformOpenapiRecordAppOpenapiDaySummaryGateway;

	/**
	 * 执行 开放平台应用开放接口日汇总 更新指令
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiRecordAppOpenapiDaySummaryVO> execute(@Valid OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand) {
		OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary = createByOpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand(openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);
		openplatformOpenapiRecordAppOpenapiDaySummary.setUpdateControl(openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);
		boolean save = openplatformOpenapiRecordAppOpenapiDaySummaryGateway.save(openplatformOpenapiRecordAppOpenapiDaySummary);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiRecordAppOpenapiDaySummaryAppStructMapping.instance.toOpenplatformOpenapiRecordAppOpenapiDaySummaryVO(openplatformOpenapiRecordAppOpenapiDaySummary));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放平台应用开放接口日汇总更新指令创建开放平台应用开放接口日汇总模型
	 * @param openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiRecordAppOpenapiDaySummary createByOpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand(OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand){
		OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary = OpenplatformOpenapiRecordAppOpenapiDaySummary.create();
		OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping.instance.fillOpenplatformOpenapiRecordAppOpenapiDaySummaryByOpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand(openplatformOpenapiRecordAppOpenapiDaySummary, openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);
		return openplatformOpenapiRecordAppOpenapiDaySummary;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping{
		OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping instance = Mappers.getMapper(OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommandToOpenplatformOpenapiRecordAppOpenapiDaySummaryMapping.class );

		default OpenplatformOpenapiRecordAppOpenapiDaySummaryId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiRecordAppOpenapiDaySummaryId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiRecordAppOpenapiDaySummary
		 * @param openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand
		 */
		void fillOpenplatformOpenapiRecordAppOpenapiDaySummaryByOpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand(@MappingTarget OpenplatformOpenapiRecordAppOpenapiDaySummary openplatformOpenapiRecordAppOpenapiDaySummary, OpenplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand openplatformOpenapiRecordAppOpenapiDaySummaryUpdateCommand);
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

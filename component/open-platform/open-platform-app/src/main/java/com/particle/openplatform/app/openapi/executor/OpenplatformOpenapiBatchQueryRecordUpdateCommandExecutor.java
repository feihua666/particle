package com.particle.openplatform.app.openapi.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordUpdateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecord;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecordId;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordGateway;
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
 * 开放接口批量查询记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway;

	/**
	 * 执行 开放接口批量查询记录 更新指令
	 * @param openplatformOpenapiBatchQueryRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordUpdateCommand openplatformOpenapiBatchQueryRecordUpdateCommand) {
		OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord = createByOpenplatformOpenapiBatchQueryRecordUpdateCommand(openplatformOpenapiBatchQueryRecordUpdateCommand);
		openplatformOpenapiBatchQueryRecord.setUpdateControl(openplatformOpenapiBatchQueryRecordUpdateCommand);
		boolean save = openplatformOpenapiBatchQueryRecordGateway.save(openplatformOpenapiBatchQueryRecord);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.toOpenplatformOpenapiBatchQueryRecordVO(openplatformOpenapiBatchQueryRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口批量查询记录更新指令创建开放接口批量查询记录模型
	 * @param openplatformOpenapiBatchQueryRecordUpdateCommand
	 * @return
	 */
	private OpenplatformOpenapiBatchQueryRecord createByOpenplatformOpenapiBatchQueryRecordUpdateCommand(OpenplatformOpenapiBatchQueryRecordUpdateCommand openplatformOpenapiBatchQueryRecordUpdateCommand){
		OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord = OpenplatformOpenapiBatchQueryRecord.create();
		OpenplatformOpenapiBatchQueryRecordUpdateCommandToOpenplatformOpenapiBatchQueryRecordMapping.instance.fillOpenplatformOpenapiBatchQueryRecordByOpenplatformOpenapiBatchQueryRecordUpdateCommand(openplatformOpenapiBatchQueryRecord, openplatformOpenapiBatchQueryRecordUpdateCommand);
		return openplatformOpenapiBatchQueryRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface OpenplatformOpenapiBatchQueryRecordUpdateCommandToOpenplatformOpenapiBatchQueryRecordMapping{
		OpenplatformOpenapiBatchQueryRecordUpdateCommandToOpenplatformOpenapiBatchQueryRecordMapping instance = Mappers.getMapper(OpenplatformOpenapiBatchQueryRecordUpdateCommandToOpenplatformOpenapiBatchQueryRecordMapping.class );

		default OpenplatformOpenapiBatchQueryRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return OpenplatformOpenapiBatchQueryRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiBatchQueryRecord
		 * @param openplatformOpenapiBatchQueryRecordUpdateCommand
		 */
		void fillOpenplatformOpenapiBatchQueryRecordByOpenplatformOpenapiBatchQueryRecordUpdateCommand(@MappingTarget OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord, OpenplatformOpenapiBatchQueryRecordUpdateCommand openplatformOpenapiBatchQueryRecordUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param openplatformOpenapiBatchQueryRecordGateway
	 */
	@Autowired
	public void setOpenplatformOpenapiBatchQueryRecordGateway(OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway) {
		this.openplatformOpenapiBatchQueryRecordGateway = openplatformOpenapiBatchQueryRecordGateway;
	}
}

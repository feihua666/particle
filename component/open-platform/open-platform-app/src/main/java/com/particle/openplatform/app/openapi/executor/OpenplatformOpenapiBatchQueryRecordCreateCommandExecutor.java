package com.particle.openplatform.app.openapi.executor;

import com.particle.openplatform.app.openapi.structmapping.OpenplatformOpenapiBatchQueryRecordAppStructMapping;
import com.particle.openplatform.client.openapi.dto.command.OpenplatformOpenapiBatchQueryRecordCreateCommand;
import com.particle.openplatform.client.openapi.dto.data.OpenplatformOpenapiBatchQueryRecordVO;
import com.particle.openplatform.domain.openapi.OpenplatformOpenapiBatchQueryRecord;
import com.particle.openplatform.domain.openapi.gateway.OpenplatformOpenapiBatchQueryRecordGateway;
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
 * 开放接口批量查询记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-09-19 11:44:36
 */
@Component
@Validated
public class OpenplatformOpenapiBatchQueryRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private OpenplatformOpenapiBatchQueryRecordGateway openplatformOpenapiBatchQueryRecordGateway;

	/**
	 * 执行开放接口批量查询记录添加指令
	 * @param openplatformOpenapiBatchQueryRecordCreateCommand
	 * @return
	 */
	public SingleResponse<OpenplatformOpenapiBatchQueryRecordVO> execute(@Valid OpenplatformOpenapiBatchQueryRecordCreateCommand openplatformOpenapiBatchQueryRecordCreateCommand) {
		OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord = createByOpenplatformOpenapiBatchQueryRecordCreateCommand(openplatformOpenapiBatchQueryRecordCreateCommand);
		openplatformOpenapiBatchQueryRecord.setAddControl(openplatformOpenapiBatchQueryRecordCreateCommand);
		boolean save = openplatformOpenapiBatchQueryRecordGateway.save(openplatformOpenapiBatchQueryRecord);
		if (save) {
			return SingleResponse.of(OpenplatformOpenapiBatchQueryRecordAppStructMapping.instance.toOpenplatformOpenapiBatchQueryRecordVO(openplatformOpenapiBatchQueryRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据开放接口批量查询记录创建指令创建开放接口批量查询记录模型
	 * @param openplatformOpenapiBatchQueryRecordCreateCommand
	 * @return
	 */
	private OpenplatformOpenapiBatchQueryRecord createByOpenplatformOpenapiBatchQueryRecordCreateCommand(OpenplatformOpenapiBatchQueryRecordCreateCommand openplatformOpenapiBatchQueryRecordCreateCommand){
		OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord = OpenplatformOpenapiBatchQueryRecord.create();
		OpenplatformOpenapiBatchQueryRecordCreateCommandToOpenplatformOpenapiBatchQueryRecordMapping.instance.fillOpenplatformOpenapiBatchQueryRecordByOpenplatformOpenapiBatchQueryRecordCreateCommand(openplatformOpenapiBatchQueryRecord, openplatformOpenapiBatchQueryRecordCreateCommand);
		return openplatformOpenapiBatchQueryRecord;
	}

	@Mapper
	interface  OpenplatformOpenapiBatchQueryRecordCreateCommandToOpenplatformOpenapiBatchQueryRecordMapping{
		OpenplatformOpenapiBatchQueryRecordCreateCommandToOpenplatformOpenapiBatchQueryRecordMapping instance = Mappers.getMapper( OpenplatformOpenapiBatchQueryRecordCreateCommandToOpenplatformOpenapiBatchQueryRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param openplatformOpenapiBatchQueryRecord
		 * @param openplatformOpenapiBatchQueryRecordCreateCommand
		 */
		void fillOpenplatformOpenapiBatchQueryRecordByOpenplatformOpenapiBatchQueryRecordCreateCommand(@MappingTarget OpenplatformOpenapiBatchQueryRecord openplatformOpenapiBatchQueryRecord, OpenplatformOpenapiBatchQueryRecordCreateCommand openplatformOpenapiBatchQueryRecordCreateCommand);
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

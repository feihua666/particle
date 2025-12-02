package com.particle.data.app.dynamictable.executor;

import com.particle.data.app.dynamictable.structmapping.DynamicTableUploadRecordAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.gateway.DynamicTableUploadRecordGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.Valid;

/**
 * <p>
 * 动态数据表格上传记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:17
 */
@Component
@Validated
public class DynamicTableUploadRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway;

	/**
	 * 执行动态数据表格上传记录添加指令
	 * @param dynamicTableUploadRecordCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableUploadRecordVO> execute(@Valid DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand) {
		DynamicTableUploadRecord dynamicTableUploadRecord = createByDynamicTableUploadRecordCreateCommand(dynamicTableUploadRecordCreateCommand);
		dynamicTableUploadRecord.setAddControl(dynamicTableUploadRecordCreateCommand);
		boolean save = dynamicTableUploadRecordGateway.save(dynamicTableUploadRecord);
		if (save) {
			return SingleResponse.of(DynamicTableUploadRecordAppStructMapping.instance.toDynamicTableUploadRecordVO(dynamicTableUploadRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据表格上传记录创建指令创建动态数据表格上传记录模型
	 * @param dynamicTableUploadRecordCreateCommand
	 * @return
	 */
	private DynamicTableUploadRecord createByDynamicTableUploadRecordCreateCommand(DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand){
		DynamicTableUploadRecord dynamicTableUploadRecord = DynamicTableUploadRecord.create();
		DynamicTableUploadRecordCreateCommandToDynamicTableUploadRecordMapping.instance.fillDynamicTableUploadRecordByDynamicTableUploadRecordCreateCommand(dynamicTableUploadRecord, dynamicTableUploadRecordCreateCommand);
		return dynamicTableUploadRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicTableUploadRecordCreateCommandToDynamicTableUploadRecordMapping{
		DynamicTableUploadRecordCreateCommandToDynamicTableUploadRecordMapping instance = Mappers.getMapper( DynamicTableUploadRecordCreateCommandToDynamicTableUploadRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicTableUploadRecord
		 * @param dynamicTableUploadRecordCreateCommand
		 */
		void fillDynamicTableUploadRecordByDynamicTableUploadRecordCreateCommand(@MappingTarget DynamicTableUploadRecord dynamicTableUploadRecord, DynamicTableUploadRecordCreateCommand dynamicTableUploadRecordCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicTableUploadRecordGateway
	 */
	@Autowired
	public void setDynamicTableUploadRecordGateway(DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway) {
		this.dynamicTableUploadRecordGateway = dynamicTableUploadRecordGateway;
	}
}

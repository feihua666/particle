package com.particle.data.app.dynamictable.executor;

import com.particle.data.app.dynamictable.structmapping.DynamicTableUploadRecordAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUploadRecordUpdateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableUploadRecordVO;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecord;
import com.particle.data.domain.dynamictable.DynamicTableUploadRecordId;
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
 * 动态数据表格上传记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicTableUploadRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableUploadRecordGateway dynamicTableUploadRecordGateway;

	/**
	 * 执行 动态数据表格上传记录 更新指令
	 * @param dynamicTableUploadRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableUploadRecordVO> execute(@Valid DynamicTableUploadRecordUpdateCommand dynamicTableUploadRecordUpdateCommand) {
		DynamicTableUploadRecord dynamicTableUploadRecord = createByDynamicTableUploadRecordUpdateCommand(dynamicTableUploadRecordUpdateCommand);
		dynamicTableUploadRecord.setUpdateControl(dynamicTableUploadRecordUpdateCommand);
		boolean save = dynamicTableUploadRecordGateway.save(dynamicTableUploadRecord);
		if (save) {
			return SingleResponse.of(DynamicTableUploadRecordAppStructMapping.instance.toDynamicTableUploadRecordVO(dynamicTableUploadRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据表格上传记录更新指令创建动态数据表格上传记录模型
	 * @param dynamicTableUploadRecordUpdateCommand
	 * @return
	 */
	private DynamicTableUploadRecord createByDynamicTableUploadRecordUpdateCommand(DynamicTableUploadRecordUpdateCommand dynamicTableUploadRecordUpdateCommand){
		DynamicTableUploadRecord dynamicTableUploadRecord = DynamicTableUploadRecord.create();
		DynamicTableUploadRecordUpdateCommandToDynamicTableUploadRecordMapping.instance.fillDynamicTableUploadRecordByDynamicTableUploadRecordUpdateCommand(dynamicTableUploadRecord, dynamicTableUploadRecordUpdateCommand);
		return dynamicTableUploadRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicTableUploadRecordUpdateCommandToDynamicTableUploadRecordMapping{
		DynamicTableUploadRecordUpdateCommandToDynamicTableUploadRecordMapping instance = Mappers.getMapper(DynamicTableUploadRecordUpdateCommandToDynamicTableUploadRecordMapping.class );

		default DynamicTableUploadRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicTableUploadRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicTableUploadRecord
		 * @param dynamicTableUploadRecordUpdateCommand
		 */
		void fillDynamicTableUploadRecordByDynamicTableUploadRecordUpdateCommand(@MappingTarget DynamicTableUploadRecord dynamicTableUploadRecord, DynamicTableUploadRecordUpdateCommand dynamicTableUploadRecordUpdateCommand);
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

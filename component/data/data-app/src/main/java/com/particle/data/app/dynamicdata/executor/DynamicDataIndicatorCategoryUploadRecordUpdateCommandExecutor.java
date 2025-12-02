package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryUploadRecordAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordUpdateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecordId;
import com.particle.data.domain.dynamicdata.gateway.DynamicDataIndicatorCategoryUploadRecordGateway;
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
 * 动态数据指标分类上传记录 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryUploadRecordUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryUploadRecordGateway dynamicDataIndicatorCategoryUploadRecordGateway;

	/**
	 * 执行 动态数据指标分类上传记录 更新指令
	 * @param dynamicDataIndicatorCategoryUploadRecordUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> execute(@Valid DynamicDataIndicatorCategoryUploadRecordUpdateCommand dynamicDataIndicatorCategoryUploadRecordUpdateCommand) {
		DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord = createByDynamicDataIndicatorCategoryUploadRecordUpdateCommand(dynamicDataIndicatorCategoryUploadRecordUpdateCommand);
		dynamicDataIndicatorCategoryUploadRecord.setUpdateControl(dynamicDataIndicatorCategoryUploadRecordUpdateCommand);
		boolean save = dynamicDataIndicatorCategoryUploadRecordGateway.save(dynamicDataIndicatorCategoryUploadRecord);
		if (save) {
			return SingleResponse.of(DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.toDynamicDataIndicatorCategoryUploadRecordVO(dynamicDataIndicatorCategoryUploadRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据指标分类上传记录更新指令创建动态数据指标分类上传记录模型
	 * @param dynamicDataIndicatorCategoryUploadRecordUpdateCommand
	 * @return
	 */
	private DynamicDataIndicatorCategoryUploadRecord createByDynamicDataIndicatorCategoryUploadRecordUpdateCommand(DynamicDataIndicatorCategoryUploadRecordUpdateCommand dynamicDataIndicatorCategoryUploadRecordUpdateCommand){
		DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord = DynamicDataIndicatorCategoryUploadRecord.create();
		DynamicDataIndicatorCategoryUploadRecordUpdateCommandToDynamicDataIndicatorCategoryUploadRecordMapping.instance.fillDynamicDataIndicatorCategoryUploadRecordByDynamicDataIndicatorCategoryUploadRecordUpdateCommand(dynamicDataIndicatorCategoryUploadRecord, dynamicDataIndicatorCategoryUploadRecordUpdateCommand);
		return dynamicDataIndicatorCategoryUploadRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicDataIndicatorCategoryUploadRecordUpdateCommandToDynamicDataIndicatorCategoryUploadRecordMapping{
		DynamicDataIndicatorCategoryUploadRecordUpdateCommandToDynamicDataIndicatorCategoryUploadRecordMapping instance = Mappers.getMapper(DynamicDataIndicatorCategoryUploadRecordUpdateCommandToDynamicDataIndicatorCategoryUploadRecordMapping.class );

		default DynamicDataIndicatorCategoryUploadRecordId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicDataIndicatorCategoryUploadRecordId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataIndicatorCategoryUploadRecord
		 * @param dynamicDataIndicatorCategoryUploadRecordUpdateCommand
		 */
		void fillDynamicDataIndicatorCategoryUploadRecordByDynamicDataIndicatorCategoryUploadRecordUpdateCommand(@MappingTarget DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord, DynamicDataIndicatorCategoryUploadRecordUpdateCommand dynamicDataIndicatorCategoryUploadRecordUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicDataIndicatorCategoryUploadRecordGateway
	 */
	@Autowired
	public void setDynamicDataIndicatorCategoryUploadRecordGateway(DynamicDataIndicatorCategoryUploadRecordGateway dynamicDataIndicatorCategoryUploadRecordGateway) {
		this.dynamicDataIndicatorCategoryUploadRecordGateway = dynamicDataIndicatorCategoryUploadRecordGateway;
	}
}

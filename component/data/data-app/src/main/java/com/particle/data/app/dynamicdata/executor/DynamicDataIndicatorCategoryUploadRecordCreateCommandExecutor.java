package com.particle.data.app.dynamicdata.executor;

import com.particle.data.app.dynamicdata.structmapping.DynamicDataIndicatorCategoryUploadRecordAppStructMapping;
import com.particle.data.client.dynamicdata.dto.command.DynamicDataIndicatorCategoryUploadRecordCreateCommand;
import com.particle.data.client.dynamicdata.dto.data.DynamicDataIndicatorCategoryUploadRecordVO;
import com.particle.data.domain.dynamicdata.DynamicDataIndicatorCategoryUploadRecord;
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
 * 动态数据指标分类上传记录 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-28 15:00:59
 */
@Component
@Validated
public class DynamicDataIndicatorCategoryUploadRecordCreateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicDataIndicatorCategoryUploadRecordGateway dynamicDataIndicatorCategoryUploadRecordGateway;

	/**
	 * 执行动态数据指标分类上传记录添加指令
	 * @param dynamicDataIndicatorCategoryUploadRecordCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicDataIndicatorCategoryUploadRecordVO> execute(@Valid DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand) {
		DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord = createByDynamicDataIndicatorCategoryUploadRecordCreateCommand(dynamicDataIndicatorCategoryUploadRecordCreateCommand);
		dynamicDataIndicatorCategoryUploadRecord.setAddControl(dynamicDataIndicatorCategoryUploadRecordCreateCommand);
		boolean save = dynamicDataIndicatorCategoryUploadRecordGateway.save(dynamicDataIndicatorCategoryUploadRecord);
		if (save) {
			return SingleResponse.of(DynamicDataIndicatorCategoryUploadRecordAppStructMapping.instance.toDynamicDataIndicatorCategoryUploadRecordVO(dynamicDataIndicatorCategoryUploadRecord));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据指标分类上传记录创建指令创建动态数据指标分类上传记录模型
	 * @param dynamicDataIndicatorCategoryUploadRecordCreateCommand
	 * @return
	 */
	private DynamicDataIndicatorCategoryUploadRecord createByDynamicDataIndicatorCategoryUploadRecordCreateCommand(DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand){
		DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord = DynamicDataIndicatorCategoryUploadRecord.create();
		DynamicDataIndicatorCategoryUploadRecordCreateCommandToDynamicDataIndicatorCategoryUploadRecordMapping.instance.fillDynamicDataIndicatorCategoryUploadRecordByDynamicDataIndicatorCategoryUploadRecordCreateCommand(dynamicDataIndicatorCategoryUploadRecord, dynamicDataIndicatorCategoryUploadRecordCreateCommand);
		return dynamicDataIndicatorCategoryUploadRecord;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicDataIndicatorCategoryUploadRecordCreateCommandToDynamicDataIndicatorCategoryUploadRecordMapping{
		DynamicDataIndicatorCategoryUploadRecordCreateCommandToDynamicDataIndicatorCategoryUploadRecordMapping instance = Mappers.getMapper( DynamicDataIndicatorCategoryUploadRecordCreateCommandToDynamicDataIndicatorCategoryUploadRecordMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicDataIndicatorCategoryUploadRecord
		 * @param dynamicDataIndicatorCategoryUploadRecordCreateCommand
		 */
		void fillDynamicDataIndicatorCategoryUploadRecordByDynamicDataIndicatorCategoryUploadRecordCreateCommand(@MappingTarget DynamicDataIndicatorCategoryUploadRecord dynamicDataIndicatorCategoryUploadRecord, DynamicDataIndicatorCategoryUploadRecordCreateCommand dynamicDataIndicatorCategoryUploadRecordCreateCommand);
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

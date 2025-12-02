package com.particle.data.app.dynamictable.executor;

import com.particle.data.app.dynamictable.structmapping.DynamicTableFieldAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldUpdateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.domain.dynamictable.DynamicTableField;
import com.particle.data.domain.dynamictable.DynamicTableFieldId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableFieldGateway;
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
 * 动态数据表格字段 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicTableFieldUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableFieldGateway dynamicTableFieldGateway;

	/**
	 * 执行 动态数据表格字段 更新指令
	 * @param dynamicTableFieldUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableFieldVO> execute(@Valid DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand) {
		DynamicTableField dynamicTableField = createByDynamicTableFieldUpdateCommand(dynamicTableFieldUpdateCommand);
		dynamicTableField.setUpdateControl(dynamicTableFieldUpdateCommand);
		boolean save = dynamicTableFieldGateway.save(dynamicTableField);
		if (save) {
			return SingleResponse.of(DynamicTableFieldAppStructMapping.instance.toDynamicTableFieldVO(dynamicTableField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据表格字段更新指令创建动态数据表格字段模型
	 * @param dynamicTableFieldUpdateCommand
	 * @return
	 */
	private DynamicTableField createByDynamicTableFieldUpdateCommand(DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand){
		DynamicTableField dynamicTableField = DynamicTableField.create();
		DynamicTableFieldUpdateCommandToDynamicTableFieldMapping.instance.fillDynamicTableFieldByDynamicTableFieldUpdateCommand(dynamicTableField, dynamicTableFieldUpdateCommand);
		return dynamicTableField;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicTableFieldUpdateCommandToDynamicTableFieldMapping{
		DynamicTableFieldUpdateCommandToDynamicTableFieldMapping instance = Mappers.getMapper(DynamicTableFieldUpdateCommandToDynamicTableFieldMapping.class );

		default DynamicTableFieldId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicTableFieldId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicTableField
		 * @param dynamicTableFieldUpdateCommand
		 */
		void fillDynamicTableFieldByDynamicTableFieldUpdateCommand(@MappingTarget DynamicTableField dynamicTableField, DynamicTableFieldUpdateCommand dynamicTableFieldUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicTableFieldGateway
	 */
	@Autowired
	public void setDynamicTableFieldGateway(DynamicTableFieldGateway dynamicTableFieldGateway) {
		this.dynamicTableFieldGateway = dynamicTableFieldGateway;
	}
}

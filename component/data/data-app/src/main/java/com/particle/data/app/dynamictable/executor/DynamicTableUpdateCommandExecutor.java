package com.particle.data.app.dynamictable.executor;

import com.particle.data.app.dynamictable.structmapping.DynamicTableAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.DynamicTableUpdateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import com.particle.data.domain.dynamictable.DynamicTable;
import com.particle.data.domain.dynamictable.DynamicTableId;
import com.particle.data.domain.dynamictable.gateway.DynamicTableGateway;
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
 * 动态数据表格 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DynamicTableUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableGateway dynamicTableGateway;

	/**
	 * 执行 动态数据表格 更新指令
	 * @param dynamicTableUpdateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableVO> execute(@Valid DynamicTableUpdateCommand dynamicTableUpdateCommand) {
		DynamicTable dynamicTable = createByDynamicTableUpdateCommand(dynamicTableUpdateCommand);
		dynamicTable.setUpdateControl(dynamicTableUpdateCommand);
		boolean save = dynamicTableGateway.save(dynamicTable);
		if (save) {
			return SingleResponse.of(DynamicTableAppStructMapping.instance.toDynamicTableVO(dynamicTable));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据表格更新指令创建动态数据表格模型
	 * @param dynamicTableUpdateCommand
	 * @return
	 */
	private DynamicTable createByDynamicTableUpdateCommand(DynamicTableUpdateCommand dynamicTableUpdateCommand){
		DynamicTable dynamicTable = DynamicTable.create();
		DynamicTableUpdateCommandToDynamicTableMapping.instance.fillDynamicTableByDynamicTableUpdateCommand(dynamicTable, dynamicTableUpdateCommand);
		return dynamicTable;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DynamicTableUpdateCommandToDynamicTableMapping{
		DynamicTableUpdateCommandToDynamicTableMapping instance = Mappers.getMapper(DynamicTableUpdateCommandToDynamicTableMapping.class );

		default DynamicTableId map(Long id){
			if (id == null) {
				return null;
			}
			return DynamicTableId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicTable
		 * @param dynamicTableUpdateCommand
		 */
		void fillDynamicTableByDynamicTableUpdateCommand(@MappingTarget DynamicTable dynamicTable, DynamicTableUpdateCommand dynamicTableUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicTableGateway
	 */
	@Autowired
	public void setDynamicTableGateway(DynamicTableGateway dynamicTableGateway) {
		this.dynamicTableGateway = dynamicTableGateway;
	}
}

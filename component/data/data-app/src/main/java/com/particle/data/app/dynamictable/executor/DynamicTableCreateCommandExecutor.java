package com.particle.data.app.dynamictable.executor;

import com.particle.data.app.dynamictable.structmapping.DynamicTableAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.DynamicTableCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableVO;
import com.particle.data.domain.dynamictable.DynamicTable;
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
 * 动态数据表格 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:29:35
 */
@Component
@Validated
public class DynamicTableCreateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableGateway dynamicTableGateway;

	/**
	 * 执行动态数据表格添加指令
	 * @param dynamicTableCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableVO> execute(@Valid DynamicTableCreateCommand dynamicTableCreateCommand) {
		DynamicTable dynamicTable = createByDynamicTableCreateCommand(dynamicTableCreateCommand);
        dynamicTable.initForAdd();
		dynamicTable.setAddControl(dynamicTableCreateCommand);
		boolean save = dynamicTableGateway.save(dynamicTable);
		if (save) {
			return SingleResponse.of(DynamicTableAppStructMapping.instance.toDynamicTableVO(dynamicTable));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据表格创建指令创建动态数据表格模型
	 * @param dynamicTableCreateCommand
	 * @return
	 */
	private DynamicTable createByDynamicTableCreateCommand(DynamicTableCreateCommand dynamicTableCreateCommand){
		DynamicTable dynamicTable = DynamicTable.create();
		DynamicTableCreateCommandToDynamicTableMapping.instance.fillDynamicTableByDynamicTableCreateCommand(dynamicTable, dynamicTableCreateCommand);
		return dynamicTable;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicTableCreateCommandToDynamicTableMapping{
		DynamicTableCreateCommandToDynamicTableMapping instance = Mappers.getMapper( DynamicTableCreateCommandToDynamicTableMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicTable
		 * @param dynamicTableCreateCommand
		 */
		void fillDynamicTableByDynamicTableCreateCommand(@MappingTarget DynamicTable dynamicTable, DynamicTableCreateCommand dynamicTableCreateCommand);
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

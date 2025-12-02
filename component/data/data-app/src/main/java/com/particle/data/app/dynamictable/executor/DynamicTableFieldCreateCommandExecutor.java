package com.particle.data.app.dynamictable.executor;

import com.particle.data.app.dynamictable.structmapping.DynamicTableFieldAppStructMapping;
import com.particle.data.client.dynamictable.dto.command.DynamicTableFieldCreateCommand;
import com.particle.data.client.dynamictable.dto.data.DynamicTableFieldVO;
import com.particle.data.domain.dynamictable.DynamicTableField;
import com.particle.data.domain.dynamictable.gateway.DynamicTableFieldGateway;
import com.particle.data.infrastructure.dynamictable.service.IDynamicTableService;
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
 * 动态数据表格字段 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-11-05 20:30:01
 */
@Component
@Validated
public class DynamicTableFieldCreateCommandExecutor  extends AbstractBaseExecutor {

	private DynamicTableFieldGateway dynamicTableFieldGateway;

    private IDynamicTableService iDynamicTableService;
	/**
	 * 执行动态数据表格字段添加指令
	 * @param dynamicTableFieldCreateCommand
	 * @return
	 */
	public SingleResponse<DynamicTableFieldVO> execute(@Valid DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand) {
		DynamicTableField dynamicTableField = createByDynamicTableFieldCreateCommand(dynamicTableFieldCreateCommand);
		dynamicTableField.setAddControl(dynamicTableFieldCreateCommand);
		boolean save = dynamicTableFieldGateway.save(dynamicTableField);
		if (save) {
            // 添加成功后，更新字段数量
            iDynamicTableService.updateDynamicTableFieldNum(dynamicTableField.getDynamicTableId());
			return SingleResponse.of(DynamicTableFieldAppStructMapping.instance.toDynamicTableFieldVO(dynamicTableField));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据动态数据表格字段创建指令创建动态数据表格字段模型
	 * @param dynamicTableFieldCreateCommand
	 * @return
	 */
	private DynamicTableField createByDynamicTableFieldCreateCommand(DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand){
		DynamicTableField dynamicTableField = DynamicTableField.create();
		DynamicTableFieldCreateCommandToDynamicTableFieldMapping.instance.fillDynamicTableFieldByDynamicTableFieldCreateCommand(dynamicTableField, dynamicTableFieldCreateCommand);
		return dynamicTableField;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DynamicTableFieldCreateCommandToDynamicTableFieldMapping{
		DynamicTableFieldCreateCommandToDynamicTableFieldMapping instance = Mappers.getMapper( DynamicTableFieldCreateCommandToDynamicTableFieldMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dynamicTableField
		 * @param dynamicTableFieldCreateCommand
		 */
		void fillDynamicTableFieldByDynamicTableFieldCreateCommand(@MappingTarget DynamicTableField dynamicTableField, DynamicTableFieldCreateCommand dynamicTableFieldCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dynamicTableFieldGateway
	 */
	@Autowired
	public void setDynamicTableFieldGateway(DynamicTableFieldGateway dynamicTableFieldGateway) {
		this.dynamicTableFieldGateway = dynamicTableFieldGateway;
	}
    @Autowired
    public void setIDynamicTableService(IDynamicTableService iDynamicTableService) {
        this.iDynamicTableService = iDynamicTableService;
    }
}

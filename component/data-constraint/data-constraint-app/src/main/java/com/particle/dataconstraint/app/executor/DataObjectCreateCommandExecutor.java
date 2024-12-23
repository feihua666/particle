package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dataconstraint.app.structmapping.DataObjectAppStructMapping;
import com.particle.dataconstraint.client.dto.command.DataObjectCreateCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.gateway.DataObjectGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
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
 * 数据对象 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:18
 */
@Component
@Validated
public class DataObjectCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataObjectGateway dataObjectGateway;

	/**
	 * 执行数据对象添加指令
	 * @param dataObjectCreateCommand
	 * @return
	 */
	public SingleResponse<DataObjectVO> execute(@Valid DataObjectCreateCommand dataObjectCreateCommand) {
		DataObject dataObject = createByDataObjectCreateCommand(dataObjectCreateCommand);
		dataObject.setAddControl(dataObjectCreateCommand);
		boolean save = dataObjectGateway.save(dataObject);
		if (save) {
			return SingleResponse.of(DataObjectAppStructMapping.instance.toDataObjectVO(dataObject));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据对象创建指令创建数据对象模型
	 * @param dataObjectCreateCommand
	 * @return
	 */
	private DataObject createByDataObjectCreateCommand(DataObjectCreateCommand dataObjectCreateCommand){
		DataObject dataObject = DataObject.create();
		DataObjectCreateCommandToDataObjectMapping.instance.fillDataObjectByDataObjectCreateCommand(dataObject, dataObjectCreateCommand);
		return dataObject;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataObjectCreateCommandToDataObjectMapping{
		DataObjectCreateCommandToDataObjectMapping instance = Mappers.getMapper( DataObjectCreateCommandToDataObjectMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataObject
		 * @param dataObjectCreateCommand
		 */
		void fillDataObjectByDataObjectCreateCommand(@MappingTarget DataObject dataObject, DataObjectCreateCommand dataObjectCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataObjectGateway
	 */
	@Autowired
	public void setDataObjectGateway(DataObjectGateway dataObjectGateway) {
		this.dataObjectGateway = dataObjectGateway;
	}
}

package com.particle.dataconstraint.app.executor;

import com.particle.dataconstraint.app.structmapping.DataObjectAppStructMapping;
import com.particle.dataconstraint.client.dto.command.DataObjectUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataObjectVO;
import com.particle.dataconstraint.domain.DataObject;
import com.particle.dataconstraint.domain.DataObjectId;
import com.particle.dataconstraint.domain.gateway.DataObjectGateway;
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
 * 数据对象 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataObjectUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataObjectGateway dataObjectGateway;

	/**
	 * 执行 数据对象 更新指令
	 * @param dataObjectUpdateCommand
	 * @return
	 */
	public SingleResponse<DataObjectVO> execute(@Valid DataObjectUpdateCommand dataObjectUpdateCommand) {
		DataObject dataObject = createByDataObjectUpdateCommand(dataObjectUpdateCommand);
		dataObject.setUpdateControl(dataObjectUpdateCommand);
		boolean save = dataObjectGateway.save(dataObject);
		if (save) {
			return SingleResponse.of(DataObjectAppStructMapping.instance.toDataObjectVO(dataObject));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据对象更新指令创建数据对象模型
	 * @param dataObjectUpdateCommand
	 * @return
	 */
	private DataObject createByDataObjectUpdateCommand(DataObjectUpdateCommand dataObjectUpdateCommand){
		DataObject dataObject = DataObject.create();
		DataObjectUpdateCommandToDataObjectMapping.instance.fillDataObjectByDataObjectUpdateCommand(dataObject, dataObjectUpdateCommand);
		return dataObject;
	}

	@Mapper
	interface DataObjectUpdateCommandToDataObjectMapping{
		DataObjectUpdateCommandToDataObjectMapping instance = Mappers.getMapper(DataObjectUpdateCommandToDataObjectMapping.class );

		default DataObjectId map(Long id){
			if (id == null) {
				return null;
			}
			return DataObjectId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataObject
		 * @param dataObjectUpdateCommand
		 */
		void fillDataObjectByDataObjectUpdateCommand(@MappingTarget DataObject dataObject, DataObjectUpdateCommand dataObjectUpdateCommand);
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

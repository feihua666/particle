package com.particle.dataconstraint.app.executor;

import com.particle.dataconstraint.app.structmapping.DataScopeAppStructMapping;
import com.particle.dataconstraint.client.dto.command.DataScopeCreateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.dataconstraint.domain.DataScope;
import com.particle.dataconstraint.domain.gateway.DataScopeGateway;
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
 * 数据范围 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:38
 */
@Component
@Validated
public class DataScopeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataScopeGateway dataScopeGateway;

	/**
	 * 执行数据范围添加指令
	 * @param dataScopeCreateCommand
	 * @return
	 */
	public SingleResponse<DataScopeVO> execute(@Valid DataScopeCreateCommand dataScopeCreateCommand) {
		DataScope dataScope = createByDataScopeCreateCommand(dataScopeCreateCommand);
		dataScope.setAddControl(dataScopeCreateCommand);
		boolean save = dataScopeGateway.save(dataScope);
		if (save) {
			return SingleResponse.of(DataScopeAppStructMapping.instance.toDataScopeVO(dataScope));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据范围创建指令创建数据范围模型
	 * @param dataScopeCreateCommand
	 * @return
	 */
	private DataScope createByDataScopeCreateCommand(DataScopeCreateCommand dataScopeCreateCommand){
		DataScope dataScope = DataScope.create();
		DataScopeCreateCommandToDataScopeMapping.instance.fillDataScopeByDataScopeCreateCommand(dataScope, dataScopeCreateCommand);
		return dataScope;
	}

	@Mapper
	interface  DataScopeCreateCommandToDataScopeMapping{
		DataScopeCreateCommandToDataScopeMapping instance = Mappers.getMapper( DataScopeCreateCommandToDataScopeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataScope
		 * @param dataScopeCreateCommand
		 */
		void fillDataScopeByDataScopeCreateCommand(@MappingTarget DataScope dataScope, DataScopeCreateCommand dataScopeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataScopeGateway
	 */
	@Autowired
	public void setDataScopeGateway(DataScopeGateway dataScopeGateway) {
		this.dataScopeGateway = dataScopeGateway;
	}
}

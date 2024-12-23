package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dataconstraint.app.structmapping.DataScopeAppStructMapping;
import com.particle.dataconstraint.client.dto.command.DataScopeUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeVO;
import com.particle.dataconstraint.domain.DataScope;
import com.particle.dataconstraint.domain.DataScopeId;
import com.particle.dataconstraint.domain.gateway.DataScopeGateway;
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
 * 数据范围 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataScopeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataScopeGateway dataScopeGateway;

	/**
	 * 执行 数据范围 更新指令
	 * @param dataScopeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataScopeVO> execute(@Valid DataScopeUpdateCommand dataScopeUpdateCommand) {
		DataScope dataScope = createByDataScopeUpdateCommand(dataScopeUpdateCommand);
		if (dataScopeUpdateCommand.getIsCustom()) {
			dataScope.changeConstraintContentTypeDictIdToNull();
		}
		dataScope.setUpdateControl(dataScopeUpdateCommand);
		boolean save = dataScopeGateway.save(dataScope);
		if (save) {
			return SingleResponse.of(DataScopeAppStructMapping.instance.toDataScopeVO(dataScope));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据范围更新指令创建数据范围模型
	 * @param dataScopeUpdateCommand
	 * @return
	 */
	private DataScope createByDataScopeUpdateCommand(DataScopeUpdateCommand dataScopeUpdateCommand){
		DataScope dataScope = DataScope.create();
		DataScopeUpdateCommandToDataScopeMapping.instance.fillDataScopeByDataScopeUpdateCommand(dataScope, dataScopeUpdateCommand);
		return dataScope;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataScopeUpdateCommandToDataScopeMapping{
		DataScopeUpdateCommandToDataScopeMapping instance = Mappers.getMapper(DataScopeUpdateCommandToDataScopeMapping.class );

		default DataScopeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataScopeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataScope
		 * @param dataScopeUpdateCommand
		 */
		void fillDataScopeByDataScopeUpdateCommand(@MappingTarget DataScope dataScope, DataScopeUpdateCommand dataScopeUpdateCommand);
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

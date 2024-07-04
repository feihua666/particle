package com.particle.dataconstraint.app.executor;

import com.particle.dataconstraint.app.structmapping.DataScopeCustomDataRelAppStructMapping;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelUpdateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.dataconstraint.domain.DataScopeCustomDataRel;
import com.particle.dataconstraint.domain.DataScopeCustomDataRelId;
import com.particle.dataconstraint.domain.gateway.DataScopeCustomDataRelGateway;
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
 * 数据范围自定义数据关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataScopeCustomDataRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataScopeCustomDataRelGateway dataScopeCustomDataRelGateway;

	/**
	 * 执行 数据范围自定义数据关系 更新指令
	 * @param dataScopeCustomDataRelUpdateCommand
	 * @return
	 */
	public SingleResponse<DataScopeCustomDataRelVO> execute(@Valid DataScopeCustomDataRelUpdateCommand dataScopeCustomDataRelUpdateCommand) {
		DataScopeCustomDataRel dataScopeCustomDataRel = createByDataScopeCustomDataRelUpdateCommand(dataScopeCustomDataRelUpdateCommand);
		dataScopeCustomDataRel.setUpdateControl(dataScopeCustomDataRelUpdateCommand);
		boolean save = dataScopeCustomDataRelGateway.save(dataScopeCustomDataRel);
		if (save) {
			return SingleResponse.of(DataScopeCustomDataRelAppStructMapping.instance.toDataScopeCustomDataRelVO(dataScopeCustomDataRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据范围自定义数据关系更新指令创建数据范围自定义数据关系模型
	 * @param dataScopeCustomDataRelUpdateCommand
	 * @return
	 */
	private DataScopeCustomDataRel createByDataScopeCustomDataRelUpdateCommand(DataScopeCustomDataRelUpdateCommand dataScopeCustomDataRelUpdateCommand){
		DataScopeCustomDataRel dataScopeCustomDataRel = DataScopeCustomDataRel.create();
		DataScopeCustomDataRelUpdateCommandToDataScopeCustomDataRelMapping.instance.fillDataScopeCustomDataRelByDataScopeCustomDataRelUpdateCommand(dataScopeCustomDataRel, dataScopeCustomDataRelUpdateCommand);
		return dataScopeCustomDataRel;
	}

	@Mapper
	interface DataScopeCustomDataRelUpdateCommandToDataScopeCustomDataRelMapping{
		DataScopeCustomDataRelUpdateCommandToDataScopeCustomDataRelMapping instance = Mappers.getMapper(DataScopeCustomDataRelUpdateCommandToDataScopeCustomDataRelMapping.class );

		default DataScopeCustomDataRelId map(Long id){
			if (id == null) {
				return null;
			}
			return DataScopeCustomDataRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataScopeCustomDataRel
		 * @param dataScopeCustomDataRelUpdateCommand
		 */
		void fillDataScopeCustomDataRelByDataScopeCustomDataRelUpdateCommand(@MappingTarget DataScopeCustomDataRel dataScopeCustomDataRel, DataScopeCustomDataRelUpdateCommand dataScopeCustomDataRelUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataScopeCustomDataRelGateway
	 */
	@Autowired
	public void setDataScopeCustomDataRelGateway(DataScopeCustomDataRelGateway dataScopeCustomDataRelGateway) {
		this.dataScopeCustomDataRelGateway = dataScopeCustomDataRelGateway;
	}
}

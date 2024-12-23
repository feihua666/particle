package com.particle.dataconstraint.app.executor;

import com.particle.common.app.executor.AbstractBaseExecutor;
import com.particle.dataconstraint.app.structmapping.DataScopeCustomDataRelAppStructMapping;
import com.particle.dataconstraint.client.dto.command.DataScopeCustomDataRelCreateCommand;
import com.particle.dataconstraint.client.dto.data.DataScopeCustomDataRelVO;
import com.particle.dataconstraint.domain.DataScopeCustomDataRel;
import com.particle.dataconstraint.domain.gateway.DataScopeCustomDataRelGateway;
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
 * 数据范围自定义数据关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-06-28 13:10:55
 */
@Component
@Validated
public class DataScopeCustomDataRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataScopeCustomDataRelGateway dataScopeCustomDataRelGateway;

	/**
	 * 执行数据范围自定义数据关系添加指令
	 * @param dataScopeCustomDataRelCreateCommand
	 * @return
	 */
	public SingleResponse<DataScopeCustomDataRelVO> execute(@Valid DataScopeCustomDataRelCreateCommand dataScopeCustomDataRelCreateCommand) {
		DataScopeCustomDataRel dataScopeCustomDataRel = createByDataScopeCustomDataRelCreateCommand(dataScopeCustomDataRelCreateCommand);
		dataScopeCustomDataRel.setAddControl(dataScopeCustomDataRelCreateCommand);
		boolean save = dataScopeCustomDataRelGateway.save(dataScopeCustomDataRel);
		if (save) {
			return SingleResponse.of(DataScopeCustomDataRelAppStructMapping.instance.toDataScopeCustomDataRelVO(dataScopeCustomDataRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据数据范围自定义数据关系创建指令创建数据范围自定义数据关系模型
	 * @param dataScopeCustomDataRelCreateCommand
	 * @return
	 */
	private DataScopeCustomDataRel createByDataScopeCustomDataRelCreateCommand(DataScopeCustomDataRelCreateCommand dataScopeCustomDataRelCreateCommand){
		DataScopeCustomDataRel dataScopeCustomDataRel = DataScopeCustomDataRel.create();
		DataScopeCustomDataRelCreateCommandToDataScopeCustomDataRelMapping.instance.fillDataScopeCustomDataRelByDataScopeCustomDataRelCreateCommand(dataScopeCustomDataRel, dataScopeCustomDataRelCreateCommand);
		return dataScopeCustomDataRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataScopeCustomDataRelCreateCommandToDataScopeCustomDataRelMapping{
		DataScopeCustomDataRelCreateCommandToDataScopeCustomDataRelMapping instance = Mappers.getMapper( DataScopeCustomDataRelCreateCommandToDataScopeCustomDataRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataScopeCustomDataRel
		 * @param dataScopeCustomDataRelCreateCommand
		 */
		void fillDataScopeCustomDataRelByDataScopeCustomDataRelCreateCommand(@MappingTarget DataScopeCustomDataRel dataScopeCustomDataRel, DataScopeCustomDataRelCreateCommand dataScopeCustomDataRelCreateCommand);
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

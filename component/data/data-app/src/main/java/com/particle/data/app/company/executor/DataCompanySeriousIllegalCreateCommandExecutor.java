package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanySeriousIllegalAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanySeriousIllegalCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanySeriousIllegalVO;
import com.particle.data.domain.company.DataCompanySeriousIllegal;
import com.particle.data.domain.company.gateway.DataCompanySeriousIllegalGateway;
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
 * 企业严重违法 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:45:45
 */
@Component
@Validated
public class DataCompanySeriousIllegalCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway;

	/**
	 * 执行企业严重违法添加指令
	 * @param dataCompanySeriousIllegalCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanySeriousIllegalVO> execute(@Valid DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand) {
		DataCompanySeriousIllegal dataCompanySeriousIllegal = createByDataCompanySeriousIllegalCreateCommand(dataCompanySeriousIllegalCreateCommand);
		dataCompanySeriousIllegal.initForAdd();
		dataCompanySeriousIllegal.setAddControl(dataCompanySeriousIllegalCreateCommand);
		boolean save = dataCompanySeriousIllegalGateway.save(dataCompanySeriousIllegal);
		if (save) {
			return SingleResponse.of(DataCompanySeriousIllegalAppStructMapping.instance.toDataCompanySeriousIllegalVO(dataCompanySeriousIllegal));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业严重违法创建指令创建企业严重违法模型
	 * @param dataCompanySeriousIllegalCreateCommand
	 * @return
	 */
	private DataCompanySeriousIllegal createByDataCompanySeriousIllegalCreateCommand(DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand){
		DataCompanySeriousIllegal dataCompanySeriousIllegal = DataCompanySeriousIllegal.create();
		DataCompanySeriousIllegalCreateCommandToDataCompanySeriousIllegalMapping.instance.fillDataCompanySeriousIllegalByDataCompanySeriousIllegalCreateCommand(dataCompanySeriousIllegal, dataCompanySeriousIllegalCreateCommand);
		return dataCompanySeriousIllegal;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanySeriousIllegalCreateCommandToDataCompanySeriousIllegalMapping{
		DataCompanySeriousIllegalCreateCommandToDataCompanySeriousIllegalMapping instance = Mappers.getMapper( DataCompanySeriousIllegalCreateCommandToDataCompanySeriousIllegalMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanySeriousIllegal
		 * @param dataCompanySeriousIllegalCreateCommand
		 */
		void fillDataCompanySeriousIllegalByDataCompanySeriousIllegalCreateCommand(@MappingTarget DataCompanySeriousIllegal dataCompanySeriousIllegal, DataCompanySeriousIllegalCreateCommand dataCompanySeriousIllegalCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanySeriousIllegalGateway
	 */
	@Autowired
	public void setDataCompanySeriousIllegalGateway(DataCompanySeriousIllegalGateway dataCompanySeriousIllegalGateway) {
		this.dataCompanySeriousIllegalGateway = dataCompanySeriousIllegalGateway;
	}
}

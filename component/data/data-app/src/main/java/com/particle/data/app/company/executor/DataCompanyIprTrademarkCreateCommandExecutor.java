package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkVO;
import com.particle.data.domain.company.DataCompanyIprTrademark;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkGateway;
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
 * 企业知识产权商标 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:14:45
 */
@Component
@Validated
public class DataCompanyIprTrademarkCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway;

	/**
	 * 执行企业知识产权商标添加指令
	 * @param dataCompanyIprTrademarkCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkVO> execute(@Valid DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand) {
		DataCompanyIprTrademark dataCompanyIprTrademark = createByDataCompanyIprTrademarkCreateCommand(dataCompanyIprTrademarkCreateCommand);
		dataCompanyIprTrademark.initForAdd();
		dataCompanyIprTrademark.setAddControl(dataCompanyIprTrademarkCreateCommand);
		boolean save = dataCompanyIprTrademarkGateway.save(dataCompanyIprTrademark);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkAppStructMapping.instance.toDataCompanyIprTrademarkVO(dataCompanyIprTrademark));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标创建指令创建企业知识产权商标模型
	 * @param dataCompanyIprTrademarkCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademark createByDataCompanyIprTrademarkCreateCommand(DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand){
		DataCompanyIprTrademark dataCompanyIprTrademark = DataCompanyIprTrademark.create();
		DataCompanyIprTrademarkCreateCommandToDataCompanyIprTrademarkMapping.instance.fillDataCompanyIprTrademarkByDataCompanyIprTrademarkCreateCommand(dataCompanyIprTrademark, dataCompanyIprTrademarkCreateCommand);
		return dataCompanyIprTrademark;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkCreateCommandToDataCompanyIprTrademarkMapping{
		DataCompanyIprTrademarkCreateCommandToDataCompanyIprTrademarkMapping instance = Mappers.getMapper( DataCompanyIprTrademarkCreateCommandToDataCompanyIprTrademarkMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademark
		 * @param dataCompanyIprTrademarkCreateCommand
		 */
		void fillDataCompanyIprTrademarkByDataCompanyIprTrademarkCreateCommand(@MappingTarget DataCompanyIprTrademark dataCompanyIprTrademark, DataCompanyIprTrademarkCreateCommand dataCompanyIprTrademarkCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkGateway(DataCompanyIprTrademarkGateway dataCompanyIprTrademarkGateway) {
		this.dataCompanyIprTrademarkGateway = dataCompanyIprTrademarkGateway;
	}
}

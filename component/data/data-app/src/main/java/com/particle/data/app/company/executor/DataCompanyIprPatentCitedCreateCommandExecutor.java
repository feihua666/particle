package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentCitedAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.domain.company.DataCompanyIprPatentCited;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCitedGateway;
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
 * 企业知识产权专利被引证信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:15
 */
@Component
@Validated
public class DataCompanyIprPatentCitedCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway;

	/**
	 * 执行企业知识产权专利被引证信息添加指令
	 * @param dataCompanyIprPatentCitedCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedVO> execute(@Valid DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand) {
		DataCompanyIprPatentCited dataCompanyIprPatentCited = createByDataCompanyIprPatentCitedCreateCommand(dataCompanyIprPatentCitedCreateCommand);
		dataCompanyIprPatentCited.initForAdd();
		dataCompanyIprPatentCited.setAddControl(dataCompanyIprPatentCitedCreateCommand);
		boolean save = dataCompanyIprPatentCitedGateway.save(dataCompanyIprPatentCited);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentCitedAppStructMapping.instance.toDataCompanyIprPatentCitedVO(dataCompanyIprPatentCited));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利被引证信息创建指令创建企业知识产权专利被引证信息模型
	 * @param dataCompanyIprPatentCitedCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentCited createByDataCompanyIprPatentCitedCreateCommand(DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand){
		DataCompanyIprPatentCited dataCompanyIprPatentCited = DataCompanyIprPatentCited.create();
		DataCompanyIprPatentCitedCreateCommandToDataCompanyIprPatentCitedMapping.instance.fillDataCompanyIprPatentCitedByDataCompanyIprPatentCitedCreateCommand(dataCompanyIprPatentCited, dataCompanyIprPatentCitedCreateCommand);
		return dataCompanyIprPatentCited;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentCitedCreateCommandToDataCompanyIprPatentCitedMapping{
		DataCompanyIprPatentCitedCreateCommandToDataCompanyIprPatentCitedMapping instance = Mappers.getMapper( DataCompanyIprPatentCitedCreateCommandToDataCompanyIprPatentCitedMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentCited
		 * @param dataCompanyIprPatentCitedCreateCommand
		 */
		void fillDataCompanyIprPatentCitedByDataCompanyIprPatentCitedCreateCommand(@MappingTarget DataCompanyIprPatentCited dataCompanyIprPatentCited, DataCompanyIprPatentCitedCreateCommand dataCompanyIprPatentCitedCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCitedGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCitedGateway(DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway) {
		this.dataCompanyIprPatentCitedGateway = dataCompanyIprPatentCitedGateway;
	}
}

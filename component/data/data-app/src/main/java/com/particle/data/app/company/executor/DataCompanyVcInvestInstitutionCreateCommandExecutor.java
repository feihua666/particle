package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcInvestInstitutionAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.domain.company.DataCompanyVcInvestInstitution;
import com.particle.data.domain.company.gateway.DataCompanyVcInvestInstitutionGateway;
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
 * 企业投资机构 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:13
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcInvestInstitutionGateway dataCompanyVcInvestInstitutionGateway;

	/**
	 * 执行企业投资机构添加指令
	 * @param dataCompanyVcInvestInstitutionCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionVO> execute(@Valid DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand) {
		DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution = createByDataCompanyVcInvestInstitutionCreateCommand(dataCompanyVcInvestInstitutionCreateCommand);
		dataCompanyVcInvestInstitution.setAddControl(dataCompanyVcInvestInstitutionCreateCommand);
		boolean save = dataCompanyVcInvestInstitutionGateway.save(dataCompanyVcInvestInstitution);
		if (save) {
			return SingleResponse.of(DataCompanyVcInvestInstitutionAppStructMapping.instance.toDataCompanyVcInvestInstitutionVO(dataCompanyVcInvestInstitution));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业投资机构创建指令创建企业投资机构模型
	 * @param dataCompanyVcInvestInstitutionCreateCommand
	 * @return
	 */
	private DataCompanyVcInvestInstitution createByDataCompanyVcInvestInstitutionCreateCommand(DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand){
		DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution = DataCompanyVcInvestInstitution.create();
		DataCompanyVcInvestInstitutionCreateCommandToDataCompanyVcInvestInstitutionMapping.instance.fillDataCompanyVcInvestInstitutionByDataCompanyVcInvestInstitutionCreateCommand(dataCompanyVcInvestInstitution, dataCompanyVcInvestInstitutionCreateCommand);
		return dataCompanyVcInvestInstitution;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyVcInvestInstitutionCreateCommandToDataCompanyVcInvestInstitutionMapping{
		DataCompanyVcInvestInstitutionCreateCommandToDataCompanyVcInvestInstitutionMapping instance = Mappers.getMapper( DataCompanyVcInvestInstitutionCreateCommandToDataCompanyVcInvestInstitutionMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcInvestInstitution
		 * @param dataCompanyVcInvestInstitutionCreateCommand
		 */
		void fillDataCompanyVcInvestInstitutionByDataCompanyVcInvestInstitutionCreateCommand(@MappingTarget DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution, DataCompanyVcInvestInstitutionCreateCommand dataCompanyVcInvestInstitutionCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcInvestInstitutionGateway
	 */
	@Autowired
	public void setDataCompanyVcInvestInstitutionGateway(DataCompanyVcInvestInstitutionGateway dataCompanyVcInvestInstitutionGateway) {
		this.dataCompanyVcInvestInstitutionGateway = dataCompanyVcInvestInstitutionGateway;
	}
}

package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcFinancingInvestInstitutionRelAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRel;
import com.particle.data.domain.company.gateway.DataCompanyVcFinancingInvestInstitutionRelGateway;
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
 * 企业融资历史投资机构关系 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:46:28
 */
@Component
@Validated
public class DataCompanyVcFinancingInvestInstitutionRelCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway;

	/**
	 * 执行企业融资历史投资机构关系添加指令
	 * @param dataCompanyVcFinancingInvestInstitutionRelCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> execute(@Valid DataCompanyVcFinancingInvestInstitutionRelCreateCommand dataCompanyVcFinancingInvestInstitutionRelCreateCommand) {
		DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel = createByDataCompanyVcFinancingInvestInstitutionRelCreateCommand(dataCompanyVcFinancingInvestInstitutionRelCreateCommand);
		dataCompanyVcFinancingInvestInstitutionRel.initForAdd();
		dataCompanyVcFinancingInvestInstitutionRel.setAddControl(dataCompanyVcFinancingInvestInstitutionRelCreateCommand);
		boolean save = dataCompanyVcFinancingInvestInstitutionRelGateway.save(dataCompanyVcFinancingInvestInstitutionRel);
		if (save) {
			return SingleResponse.of(DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.toDataCompanyVcFinancingInvestInstitutionRelVO(dataCompanyVcFinancingInvestInstitutionRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资历史投资机构关系创建指令创建企业融资历史投资机构关系模型
	 * @param dataCompanyVcFinancingInvestInstitutionRelCreateCommand
	 * @return
	 */
	private DataCompanyVcFinancingInvestInstitutionRel createByDataCompanyVcFinancingInvestInstitutionRelCreateCommand(DataCompanyVcFinancingInvestInstitutionRelCreateCommand dataCompanyVcFinancingInvestInstitutionRelCreateCommand){
		DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel = DataCompanyVcFinancingInvestInstitutionRel.create();
		DataCompanyVcFinancingInvestInstitutionRelCreateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping.instance.fillDataCompanyVcFinancingInvestInstitutionRelByDataCompanyVcFinancingInvestInstitutionRelCreateCommand(dataCompanyVcFinancingInvestInstitutionRel, dataCompanyVcFinancingInvestInstitutionRelCreateCommand);
		return dataCompanyVcFinancingInvestInstitutionRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyVcFinancingInvestInstitutionRelCreateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping{
		DataCompanyVcFinancingInvestInstitutionRelCreateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping instance = Mappers.getMapper( DataCompanyVcFinancingInvestInstitutionRelCreateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcFinancingInvestInstitutionRel
		 * @param dataCompanyVcFinancingInvestInstitutionRelCreateCommand
		 */
		void fillDataCompanyVcFinancingInvestInstitutionRelByDataCompanyVcFinancingInvestInstitutionRelCreateCommand(@MappingTarget DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel, DataCompanyVcFinancingInvestInstitutionRelCreateCommand dataCompanyVcFinancingInvestInstitutionRelCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyVcFinancingInvestInstitutionRelGateway
	 */
	@Autowired
	public void setDataCompanyVcFinancingInvestInstitutionRelGateway(DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway) {
		this.dataCompanyVcFinancingInvestInstitutionRelGateway = dataCompanyVcFinancingInvestInstitutionRelGateway;
	}
}

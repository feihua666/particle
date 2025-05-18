package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcFinancingInvestInstitutionRelAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcFinancingInvestInstitutionRelUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcFinancingInvestInstitutionRelVO;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRel;
import com.particle.data.domain.company.DataCompanyVcFinancingInvestInstitutionRelId;
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
 * 企业融资历史投资机构关系 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyVcFinancingInvestInstitutionRelUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcFinancingInvestInstitutionRelGateway dataCompanyVcFinancingInvestInstitutionRelGateway;

	/**
	 * 执行 企业融资历史投资机构关系 更新指令
	 * @param dataCompanyVcFinancingInvestInstitutionRelUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcFinancingInvestInstitutionRelVO> execute(@Valid DataCompanyVcFinancingInvestInstitutionRelUpdateCommand dataCompanyVcFinancingInvestInstitutionRelUpdateCommand) {
		DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel = createByDataCompanyVcFinancingInvestInstitutionRelUpdateCommand(dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);
		dataCompanyVcFinancingInvestInstitutionRel.initForUpdate();
		dataCompanyVcFinancingInvestInstitutionRel.setUpdateControl(dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);
		boolean save = dataCompanyVcFinancingInvestInstitutionRelGateway.save(dataCompanyVcFinancingInvestInstitutionRel);
		if (save) {
			return SingleResponse.of(DataCompanyVcFinancingInvestInstitutionRelAppStructMapping.instance.toDataCompanyVcFinancingInvestInstitutionRelVO(dataCompanyVcFinancingInvestInstitutionRel));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业融资历史投资机构关系更新指令创建企业融资历史投资机构关系模型
	 * @param dataCompanyVcFinancingInvestInstitutionRelUpdateCommand
	 * @return
	 */
	private DataCompanyVcFinancingInvestInstitutionRel createByDataCompanyVcFinancingInvestInstitutionRelUpdateCommand(DataCompanyVcFinancingInvestInstitutionRelUpdateCommand dataCompanyVcFinancingInvestInstitutionRelUpdateCommand){
		DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel = DataCompanyVcFinancingInvestInstitutionRel.create();
		DataCompanyVcFinancingInvestInstitutionRelUpdateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping.instance.fillDataCompanyVcFinancingInvestInstitutionRelByDataCompanyVcFinancingInvestInstitutionRelUpdateCommand(dataCompanyVcFinancingInvestInstitutionRel, dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);
		return dataCompanyVcFinancingInvestInstitutionRel;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyVcFinancingInvestInstitutionRelUpdateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping{
		DataCompanyVcFinancingInvestInstitutionRelUpdateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping instance = Mappers.getMapper(DataCompanyVcFinancingInvestInstitutionRelUpdateCommandToDataCompanyVcFinancingInvestInstitutionRelMapping.class );

		default DataCompanyVcFinancingInvestInstitutionRelId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyVcFinancingInvestInstitutionRelId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcFinancingInvestInstitutionRel
		 * @param dataCompanyVcFinancingInvestInstitutionRelUpdateCommand
		 */
		void fillDataCompanyVcFinancingInvestInstitutionRelByDataCompanyVcFinancingInvestInstitutionRelUpdateCommand(@MappingTarget DataCompanyVcFinancingInvestInstitutionRel dataCompanyVcFinancingInvestInstitutionRel, DataCompanyVcFinancingInvestInstitutionRelUpdateCommand dataCompanyVcFinancingInvestInstitutionRelUpdateCommand);
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

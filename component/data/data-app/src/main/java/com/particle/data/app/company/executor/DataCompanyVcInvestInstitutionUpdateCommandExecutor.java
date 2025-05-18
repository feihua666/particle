package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyVcInvestInstitutionAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyVcInvestInstitutionUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVcInvestInstitutionVO;
import com.particle.data.domain.company.DataCompanyVcInvestInstitution;
import com.particle.data.domain.company.DataCompanyVcInvestInstitutionId;
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
 * 企业投资机构 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyVcInvestInstitutionUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyVcInvestInstitutionGateway dataCompanyVcInvestInstitutionGateway;

	/**
	 * 执行 企业投资机构 更新指令
	 * @param dataCompanyVcInvestInstitutionUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVcInvestInstitutionVO> execute(@Valid DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand) {
		DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution = createByDataCompanyVcInvestInstitutionUpdateCommand(dataCompanyVcInvestInstitutionUpdateCommand);
		dataCompanyVcInvestInstitution.setUpdateControl(dataCompanyVcInvestInstitutionUpdateCommand);
		boolean save = dataCompanyVcInvestInstitutionGateway.save(dataCompanyVcInvestInstitution);
		if (save) {
			return SingleResponse.of(DataCompanyVcInvestInstitutionAppStructMapping.instance.toDataCompanyVcInvestInstitutionVO(dataCompanyVcInvestInstitution));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业投资机构更新指令创建企业投资机构模型
	 * @param dataCompanyVcInvestInstitutionUpdateCommand
	 * @return
	 */
	private DataCompanyVcInvestInstitution createByDataCompanyVcInvestInstitutionUpdateCommand(DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand){
		DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution = DataCompanyVcInvestInstitution.create();
		DataCompanyVcInvestInstitutionUpdateCommandToDataCompanyVcInvestInstitutionMapping.instance.fillDataCompanyVcInvestInstitutionByDataCompanyVcInvestInstitutionUpdateCommand(dataCompanyVcInvestInstitution, dataCompanyVcInvestInstitutionUpdateCommand);
		return dataCompanyVcInvestInstitution;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyVcInvestInstitutionUpdateCommandToDataCompanyVcInvestInstitutionMapping{
		DataCompanyVcInvestInstitutionUpdateCommandToDataCompanyVcInvestInstitutionMapping instance = Mappers.getMapper(DataCompanyVcInvestInstitutionUpdateCommandToDataCompanyVcInvestInstitutionMapping.class );

		default DataCompanyVcInvestInstitutionId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyVcInvestInstitutionId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyVcInvestInstitution
		 * @param dataCompanyVcInvestInstitutionUpdateCommand
		 */
		void fillDataCompanyVcInvestInstitutionByDataCompanyVcInvestInstitutionUpdateCommand(@MappingTarget DataCompanyVcInvestInstitution dataCompanyVcInvestInstitution, DataCompanyVcInvestInstitutionUpdateCommand dataCompanyVcInvestInstitutionUpdateCommand);
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

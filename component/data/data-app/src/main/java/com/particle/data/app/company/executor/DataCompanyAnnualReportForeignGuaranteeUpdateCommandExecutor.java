package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignGuaranteeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuarantee;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuaranteeId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignGuaranteeGateway;
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
 * 企业年报对外担保 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway;

	/**
	 * 执行 企业年报对外担保 更新指令
	 * @param dataCompanyAnnualReportForeignGuaranteeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> execute(@Valid DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand) {
		DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee = createByDataCompanyAnnualReportForeignGuaranteeUpdateCommand(dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
		dataCompanyAnnualReportForeignGuarantee.initForUpdate();
		dataCompanyAnnualReportForeignGuarantee.setUpdateControl(dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
		boolean save = dataCompanyAnnualReportForeignGuaranteeGateway.save(dataCompanyAnnualReportForeignGuarantee);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.toDataCompanyAnnualReportForeignGuaranteeVO(dataCompanyAnnualReportForeignGuarantee));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报对外担保更新指令创建企业年报对外担保模型
	 * @param dataCompanyAnnualReportForeignGuaranteeUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportForeignGuarantee createByDataCompanyAnnualReportForeignGuaranteeUpdateCommand(DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand){
		DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee = DataCompanyAnnualReportForeignGuarantee.create();
		DataCompanyAnnualReportForeignGuaranteeUpdateCommandToDataCompanyAnnualReportForeignGuaranteeMapping.instance.fillDataCompanyAnnualReportForeignGuaranteeByDataCompanyAnnualReportForeignGuaranteeUpdateCommand(dataCompanyAnnualReportForeignGuarantee, dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
		return dataCompanyAnnualReportForeignGuarantee;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportForeignGuaranteeUpdateCommandToDataCompanyAnnualReportForeignGuaranteeMapping{
		DataCompanyAnnualReportForeignGuaranteeUpdateCommandToDataCompanyAnnualReportForeignGuaranteeMapping instance = Mappers.getMapper(DataCompanyAnnualReportForeignGuaranteeUpdateCommandToDataCompanyAnnualReportForeignGuaranteeMapping.class );

		default DataCompanyAnnualReportForeignGuaranteeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportForeignGuaranteeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportForeignGuarantee
		 * @param dataCompanyAnnualReportForeignGuaranteeUpdateCommand
		 */
		void fillDataCompanyAnnualReportForeignGuaranteeByDataCompanyAnnualReportForeignGuaranteeUpdateCommand(@MappingTarget DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee, DataCompanyAnnualReportForeignGuaranteeUpdateCommand dataCompanyAnnualReportForeignGuaranteeUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportForeignGuaranteeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportForeignGuaranteeGateway(DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway) {
		this.dataCompanyAnnualReportForeignGuaranteeGateway = dataCompanyAnnualReportForeignGuaranteeGateway;
	}
}

package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignGuaranteeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignGuaranteeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignGuaranteeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignGuarantee;
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
 * 企业年报对外担保 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:11
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignGuaranteeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignGuaranteeGateway dataCompanyAnnualReportForeignGuaranteeGateway;

	/**
	 * 执行企业年报对外担保添加指令
	 * @param dataCompanyAnnualReportForeignGuaranteeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignGuaranteeVO> execute(@Valid DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand) {
		DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee = createByDataCompanyAnnualReportForeignGuaranteeCreateCommand(dataCompanyAnnualReportForeignGuaranteeCreateCommand);
		dataCompanyAnnualReportForeignGuarantee.initForAdd();
		dataCompanyAnnualReportForeignGuarantee.setAddControl(dataCompanyAnnualReportForeignGuaranteeCreateCommand);
		boolean save = dataCompanyAnnualReportForeignGuaranteeGateway.save(dataCompanyAnnualReportForeignGuarantee);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportForeignGuaranteeAppStructMapping.instance.toDataCompanyAnnualReportForeignGuaranteeVO(dataCompanyAnnualReportForeignGuarantee));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报对外担保创建指令创建企业年报对外担保模型
	 * @param dataCompanyAnnualReportForeignGuaranteeCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportForeignGuarantee createByDataCompanyAnnualReportForeignGuaranteeCreateCommand(DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand){
		DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee = DataCompanyAnnualReportForeignGuarantee.create();
		DataCompanyAnnualReportForeignGuaranteeCreateCommandToDataCompanyAnnualReportForeignGuaranteeMapping.instance.fillDataCompanyAnnualReportForeignGuaranteeByDataCompanyAnnualReportForeignGuaranteeCreateCommand(dataCompanyAnnualReportForeignGuarantee, dataCompanyAnnualReportForeignGuaranteeCreateCommand);
		return dataCompanyAnnualReportForeignGuarantee;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportForeignGuaranteeCreateCommandToDataCompanyAnnualReportForeignGuaranteeMapping{
		DataCompanyAnnualReportForeignGuaranteeCreateCommandToDataCompanyAnnualReportForeignGuaranteeMapping instance = Mappers.getMapper( DataCompanyAnnualReportForeignGuaranteeCreateCommandToDataCompanyAnnualReportForeignGuaranteeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportForeignGuarantee
		 * @param dataCompanyAnnualReportForeignGuaranteeCreateCommand
		 */
		void fillDataCompanyAnnualReportForeignGuaranteeByDataCompanyAnnualReportForeignGuaranteeCreateCommand(@MappingTarget DataCompanyAnnualReportForeignGuarantee dataCompanyAnnualReportForeignGuarantee, DataCompanyAnnualReportForeignGuaranteeCreateCommand dataCompanyAnnualReportForeignGuaranteeCreateCommand);
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

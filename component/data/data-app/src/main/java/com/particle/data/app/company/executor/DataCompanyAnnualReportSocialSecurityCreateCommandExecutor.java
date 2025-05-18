package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportSocialSecurityAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurity;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportSocialSecurityGateway;
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
 * 企业年报社保 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:37:03
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway;

	/**
	 * 执行企业年报社保添加指令
	 * @param dataCompanyAnnualReportSocialSecurityCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> execute(@Valid DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand) {
		DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity = createByDataCompanyAnnualReportSocialSecurityCreateCommand(dataCompanyAnnualReportSocialSecurityCreateCommand);
		dataCompanyAnnualReportSocialSecurity.setAddControl(dataCompanyAnnualReportSocialSecurityCreateCommand);
		boolean save = dataCompanyAnnualReportSocialSecurityGateway.save(dataCompanyAnnualReportSocialSecurity);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.toDataCompanyAnnualReportSocialSecurityVO(dataCompanyAnnualReportSocialSecurity));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报社保创建指令创建企业年报社保模型
	 * @param dataCompanyAnnualReportSocialSecurityCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportSocialSecurity createByDataCompanyAnnualReportSocialSecurityCreateCommand(DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand){
		DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity = DataCompanyAnnualReportSocialSecurity.create();
		DataCompanyAnnualReportSocialSecurityCreateCommandToDataCompanyAnnualReportSocialSecurityMapping.instance.fillDataCompanyAnnualReportSocialSecurityByDataCompanyAnnualReportSocialSecurityCreateCommand(dataCompanyAnnualReportSocialSecurity, dataCompanyAnnualReportSocialSecurityCreateCommand);
		return dataCompanyAnnualReportSocialSecurity;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportSocialSecurityCreateCommandToDataCompanyAnnualReportSocialSecurityMapping{
		DataCompanyAnnualReportSocialSecurityCreateCommandToDataCompanyAnnualReportSocialSecurityMapping instance = Mappers.getMapper( DataCompanyAnnualReportSocialSecurityCreateCommandToDataCompanyAnnualReportSocialSecurityMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportSocialSecurity
		 * @param dataCompanyAnnualReportSocialSecurityCreateCommand
		 */
		void fillDataCompanyAnnualReportSocialSecurityByDataCompanyAnnualReportSocialSecurityCreateCommand(@MappingTarget DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity, DataCompanyAnnualReportSocialSecurityCreateCommand dataCompanyAnnualReportSocialSecurityCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportSocialSecurityGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportSocialSecurityGateway(DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway) {
		this.dataCompanyAnnualReportSocialSecurityGateway = dataCompanyAnnualReportSocialSecurityGateway;
	}
}

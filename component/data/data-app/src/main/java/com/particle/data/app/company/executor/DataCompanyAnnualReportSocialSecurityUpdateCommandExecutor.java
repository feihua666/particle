package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportSocialSecurityAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportSocialSecurityUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportSocialSecurityVO;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurity;
import com.particle.data.domain.company.DataCompanyAnnualReportSocialSecurityId;
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
 * 企业年报社保 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportSocialSecurityUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportSocialSecurityGateway dataCompanyAnnualReportSocialSecurityGateway;

	/**
	 * 执行 企业年报社保 更新指令
	 * @param dataCompanyAnnualReportSocialSecurityUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportSocialSecurityVO> execute(@Valid DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand) {
		DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity = createByDataCompanyAnnualReportSocialSecurityUpdateCommand(dataCompanyAnnualReportSocialSecurityUpdateCommand);
		dataCompanyAnnualReportSocialSecurity.setUpdateControl(dataCompanyAnnualReportSocialSecurityUpdateCommand);
		boolean save = dataCompanyAnnualReportSocialSecurityGateway.save(dataCompanyAnnualReportSocialSecurity);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportSocialSecurityAppStructMapping.instance.toDataCompanyAnnualReportSocialSecurityVO(dataCompanyAnnualReportSocialSecurity));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报社保更新指令创建企业年报社保模型
	 * @param dataCompanyAnnualReportSocialSecurityUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportSocialSecurity createByDataCompanyAnnualReportSocialSecurityUpdateCommand(DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand){
		DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity = DataCompanyAnnualReportSocialSecurity.create();
		DataCompanyAnnualReportSocialSecurityUpdateCommandToDataCompanyAnnualReportSocialSecurityMapping.instance.fillDataCompanyAnnualReportSocialSecurityByDataCompanyAnnualReportSocialSecurityUpdateCommand(dataCompanyAnnualReportSocialSecurity, dataCompanyAnnualReportSocialSecurityUpdateCommand);
		return dataCompanyAnnualReportSocialSecurity;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportSocialSecurityUpdateCommandToDataCompanyAnnualReportSocialSecurityMapping{
		DataCompanyAnnualReportSocialSecurityUpdateCommandToDataCompanyAnnualReportSocialSecurityMapping instance = Mappers.getMapper(DataCompanyAnnualReportSocialSecurityUpdateCommandToDataCompanyAnnualReportSocialSecurityMapping.class );

		default DataCompanyAnnualReportSocialSecurityId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportSocialSecurityId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportSocialSecurity
		 * @param dataCompanyAnnualReportSocialSecurityUpdateCommand
		 */
		void fillDataCompanyAnnualReportSocialSecurityByDataCompanyAnnualReportSocialSecurityUpdateCommand(@MappingTarget DataCompanyAnnualReportSocialSecurity dataCompanyAnnualReportSocialSecurity, DataCompanyAnnualReportSocialSecurityUpdateCommand dataCompanyAnnualReportSocialSecurityUpdateCommand);
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

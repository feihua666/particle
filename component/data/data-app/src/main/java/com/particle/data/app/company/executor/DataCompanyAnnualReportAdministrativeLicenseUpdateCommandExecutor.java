package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportAdministrativeLicenseVO;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAnnualReportAdministrativeLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportAdministrativeLicenseGateway;
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
 * 企业年报行政许可 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportAdministrativeLicenseUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway;

	/**
	 * 执行 企业年报行政许可 更新指令
	 * @param dataCompanyAnnualReportAdministrativeLicenseUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportAdministrativeLicenseVO> execute(@Valid DataCompanyAnnualReportAdministrativeLicenseUpdateCommand dataCompanyAnnualReportAdministrativeLicenseUpdateCommand) {
		DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense = createByDataCompanyAnnualReportAdministrativeLicenseUpdateCommand(dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);
		dataCompanyAnnualReportAdministrativeLicense.setUpdateControl(dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);
		dataCompanyAnnualReportAdministrativeLicense.initForUpdate();
		boolean save = dataCompanyAnnualReportAdministrativeLicenseGateway.save(dataCompanyAnnualReportAdministrativeLicense);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportAdministrativeLicenseAppStructMapping.instance.toDataCompanyAnnualReportAdministrativeLicenseVO(dataCompanyAnnualReportAdministrativeLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报行政许可更新指令创建企业年报行政许可模型
	 * @param dataCompanyAnnualReportAdministrativeLicenseUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportAdministrativeLicense createByDataCompanyAnnualReportAdministrativeLicenseUpdateCommand(DataCompanyAnnualReportAdministrativeLicenseUpdateCommand dataCompanyAnnualReportAdministrativeLicenseUpdateCommand){
		DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense = DataCompanyAnnualReportAdministrativeLicense.create();
		DataCompanyAnnualReportAdministrativeLicenseUpdateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping.instance.fillDataCompanyAnnualReportAdministrativeLicenseByDataCompanyAnnualReportAdministrativeLicenseUpdateCommand(dataCompanyAnnualReportAdministrativeLicense, dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);
		return dataCompanyAnnualReportAdministrativeLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportAdministrativeLicenseUpdateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping{
		DataCompanyAnnualReportAdministrativeLicenseUpdateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping instance = Mappers.getMapper(DataCompanyAnnualReportAdministrativeLicenseUpdateCommandToDataCompanyAnnualReportAdministrativeLicenseMapping.class );

		default DataCompanyAnnualReportAdministrativeLicenseId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportAdministrativeLicenseId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportAdministrativeLicense
		 * @param dataCompanyAnnualReportAdministrativeLicenseUpdateCommand
		 */
		void fillDataCompanyAnnualReportAdministrativeLicenseByDataCompanyAnnualReportAdministrativeLicenseUpdateCommand(@MappingTarget DataCompanyAnnualReportAdministrativeLicense dataCompanyAnnualReportAdministrativeLicense, DataCompanyAnnualReportAdministrativeLicenseUpdateCommand dataCompanyAnnualReportAdministrativeLicenseUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportAdministrativeLicenseGateway(DataCompanyAnnualReportAdministrativeLicenseGateway dataCompanyAnnualReportAdministrativeLicenseGateway) {
		this.dataCompanyAnnualReportAdministrativeLicenseGateway = dataCompanyAnnualReportAdministrativeLicenseGateway;
	}
}

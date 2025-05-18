package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportVO;
import com.particle.data.domain.company.DataCompanyAnnualReport;
import com.particle.data.domain.company.DataCompanyAnnualReportId;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportGateway;
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
 * 企业年报 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway;

	/**
	 * 执行 企业年报 更新指令
	 * @param dataCompanyAnnualReportUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportVO> execute(@Valid DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand) {
		DataCompanyAnnualReport dataCompanyAnnualReport = createByDataCompanyAnnualReportUpdateCommand(dataCompanyAnnualReportUpdateCommand);
		dataCompanyAnnualReport.setUpdateControl(dataCompanyAnnualReportUpdateCommand);
		boolean save = dataCompanyAnnualReportGateway.save(dataCompanyAnnualReport);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportAppStructMapping.instance.toDataCompanyAnnualReportVO(dataCompanyAnnualReport));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报更新指令创建企业年报模型
	 * @param dataCompanyAnnualReportUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReport createByDataCompanyAnnualReportUpdateCommand(DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand){
		DataCompanyAnnualReport dataCompanyAnnualReport = DataCompanyAnnualReport.create();
		DataCompanyAnnualReportUpdateCommandToDataCompanyAnnualReportMapping.instance.fillDataCompanyAnnualReportByDataCompanyAnnualReportUpdateCommand(dataCompanyAnnualReport, dataCompanyAnnualReportUpdateCommand);
		return dataCompanyAnnualReport;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportUpdateCommandToDataCompanyAnnualReportMapping{
		DataCompanyAnnualReportUpdateCommandToDataCompanyAnnualReportMapping instance = Mappers.getMapper(DataCompanyAnnualReportUpdateCommandToDataCompanyAnnualReportMapping.class );

		default DataCompanyAnnualReportId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReport
		 * @param dataCompanyAnnualReportUpdateCommand
		 */
		void fillDataCompanyAnnualReportByDataCompanyAnnualReportUpdateCommand(@MappingTarget DataCompanyAnnualReport dataCompanyAnnualReport, DataCompanyAnnualReportUpdateCommand dataCompanyAnnualReportUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportGateway(DataCompanyAnnualReportGateway dataCompanyAnnualReportGateway) {
		this.dataCompanyAnnualReportGateway = dataCompanyAnnualReportGateway;
	}
}

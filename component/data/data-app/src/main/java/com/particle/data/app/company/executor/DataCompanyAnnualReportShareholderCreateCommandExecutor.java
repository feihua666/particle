package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholder;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportShareholderGateway;
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
 * 企业年报股东 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:37
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway;

	/**
	 * 执行企业年报股东添加指令
	 * @param dataCompanyAnnualReportShareholderCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderVO> execute(@Valid DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand) {
		DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder = createByDataCompanyAnnualReportShareholderCreateCommand(dataCompanyAnnualReportShareholderCreateCommand);
		dataCompanyAnnualReportShareholder.setAddControl(dataCompanyAnnualReportShareholderCreateCommand);
		boolean save = dataCompanyAnnualReportShareholderGateway.save(dataCompanyAnnualReportShareholder);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportShareholderAppStructMapping.instance.toDataCompanyAnnualReportShareholderVO(dataCompanyAnnualReportShareholder));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报股东创建指令创建企业年报股东模型
	 * @param dataCompanyAnnualReportShareholderCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportShareholder createByDataCompanyAnnualReportShareholderCreateCommand(DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand){
		DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder = DataCompanyAnnualReportShareholder.create();
		DataCompanyAnnualReportShareholderCreateCommandToDataCompanyAnnualReportShareholderMapping.instance.fillDataCompanyAnnualReportShareholderByDataCompanyAnnualReportShareholderCreateCommand(dataCompanyAnnualReportShareholder, dataCompanyAnnualReportShareholderCreateCommand);
		return dataCompanyAnnualReportShareholder;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportShareholderCreateCommandToDataCompanyAnnualReportShareholderMapping{
		DataCompanyAnnualReportShareholderCreateCommandToDataCompanyAnnualReportShareholderMapping instance = Mappers.getMapper( DataCompanyAnnualReportShareholderCreateCommandToDataCompanyAnnualReportShareholderMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportShareholder
		 * @param dataCompanyAnnualReportShareholderCreateCommand
		 */
		void fillDataCompanyAnnualReportShareholderByDataCompanyAnnualReportShareholderCreateCommand(@MappingTarget DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder, DataCompanyAnnualReportShareholderCreateCommand dataCompanyAnnualReportShareholderCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportShareholderGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportShareholderGateway(DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway) {
		this.dataCompanyAnnualReportShareholderGateway = dataCompanyAnnualReportShareholderGateway;
	}
}

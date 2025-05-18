package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignInvestAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvest;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportForeignInvestGateway;
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
 * 企业年报对外投资 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:36:23
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway;

	/**
	 * 执行企业年报对外投资添加指令
	 * @param dataCompanyAnnualReportForeignInvestCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestVO> execute(@Valid DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand) {
		DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest = createByDataCompanyAnnualReportForeignInvestCreateCommand(dataCompanyAnnualReportForeignInvestCreateCommand);
		dataCompanyAnnualReportForeignInvest.initForAdd();
		dataCompanyAnnualReportForeignInvest.setAddControl(dataCompanyAnnualReportForeignInvestCreateCommand);
		boolean save = dataCompanyAnnualReportForeignInvestGateway.save(dataCompanyAnnualReportForeignInvest);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportForeignInvestAppStructMapping.instance.toDataCompanyAnnualReportForeignInvestVO(dataCompanyAnnualReportForeignInvest));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报对外投资创建指令创建企业年报对外投资模型
	 * @param dataCompanyAnnualReportForeignInvestCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportForeignInvest createByDataCompanyAnnualReportForeignInvestCreateCommand(DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand){
		DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest = DataCompanyAnnualReportForeignInvest.create();
		DataCompanyAnnualReportForeignInvestCreateCommandToDataCompanyAnnualReportForeignInvestMapping.instance.fillDataCompanyAnnualReportForeignInvestByDataCompanyAnnualReportForeignInvestCreateCommand(dataCompanyAnnualReportForeignInvest, dataCompanyAnnualReportForeignInvestCreateCommand);
		return dataCompanyAnnualReportForeignInvest;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportForeignInvestCreateCommandToDataCompanyAnnualReportForeignInvestMapping{
		DataCompanyAnnualReportForeignInvestCreateCommandToDataCompanyAnnualReportForeignInvestMapping instance = Mappers.getMapper( DataCompanyAnnualReportForeignInvestCreateCommandToDataCompanyAnnualReportForeignInvestMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportForeignInvest
		 * @param dataCompanyAnnualReportForeignInvestCreateCommand
		 */
		void fillDataCompanyAnnualReportForeignInvestByDataCompanyAnnualReportForeignInvestCreateCommand(@MappingTarget DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest, DataCompanyAnnualReportForeignInvestCreateCommand dataCompanyAnnualReportForeignInvestCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportForeignInvestGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportForeignInvestGateway(DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway) {
		this.dataCompanyAnnualReportForeignInvestGateway = dataCompanyAnnualReportForeignInvestGateway;
	}
}

package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportForeignInvestAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportForeignInvestUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportForeignInvestVO;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvest;
import com.particle.data.domain.company.DataCompanyAnnualReportForeignInvestId;
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
 * 企业年报对外投资 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportForeignInvestUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportForeignInvestGateway dataCompanyAnnualReportForeignInvestGateway;

	/**
	 * 执行 企业年报对外投资 更新指令
	 * @param dataCompanyAnnualReportForeignInvestUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportForeignInvestVO> execute(@Valid DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand) {
		DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest = createByDataCompanyAnnualReportForeignInvestUpdateCommand(dataCompanyAnnualReportForeignInvestUpdateCommand);
		dataCompanyAnnualReportForeignInvest.initForUpdate();
		dataCompanyAnnualReportForeignInvest.setUpdateControl(dataCompanyAnnualReportForeignInvestUpdateCommand);
		boolean save = dataCompanyAnnualReportForeignInvestGateway.save(dataCompanyAnnualReportForeignInvest);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportForeignInvestAppStructMapping.instance.toDataCompanyAnnualReportForeignInvestVO(dataCompanyAnnualReportForeignInvest));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报对外投资更新指令创建企业年报对外投资模型
	 * @param dataCompanyAnnualReportForeignInvestUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportForeignInvest createByDataCompanyAnnualReportForeignInvestUpdateCommand(DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand){
		DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest = DataCompanyAnnualReportForeignInvest.create();
		DataCompanyAnnualReportForeignInvestUpdateCommandToDataCompanyAnnualReportForeignInvestMapping.instance.fillDataCompanyAnnualReportForeignInvestByDataCompanyAnnualReportForeignInvestUpdateCommand(dataCompanyAnnualReportForeignInvest, dataCompanyAnnualReportForeignInvestUpdateCommand);
		return dataCompanyAnnualReportForeignInvest;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportForeignInvestUpdateCommandToDataCompanyAnnualReportForeignInvestMapping{
		DataCompanyAnnualReportForeignInvestUpdateCommandToDataCompanyAnnualReportForeignInvestMapping instance = Mappers.getMapper(DataCompanyAnnualReportForeignInvestUpdateCommandToDataCompanyAnnualReportForeignInvestMapping.class );

		default DataCompanyAnnualReportForeignInvestId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportForeignInvestId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportForeignInvest
		 * @param dataCompanyAnnualReportForeignInvestUpdateCommand
		 */
		void fillDataCompanyAnnualReportForeignInvestByDataCompanyAnnualReportForeignInvestUpdateCommand(@MappingTarget DataCompanyAnnualReportForeignInvest dataCompanyAnnualReportForeignInvest, DataCompanyAnnualReportForeignInvestUpdateCommand dataCompanyAnnualReportForeignInvestUpdateCommand);
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

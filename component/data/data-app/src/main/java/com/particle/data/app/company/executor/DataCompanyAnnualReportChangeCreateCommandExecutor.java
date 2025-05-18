package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportChangeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportChange;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportChangeGateway;
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
 * 企业年报变更 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:43
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway;

	/**
	 * 执行企业年报变更添加指令
	 * @param dataCompanyAnnualReportChangeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeVO> execute(@Valid DataCompanyAnnualReportChangeCreateCommand dataCompanyAnnualReportChangeCreateCommand) {
		DataCompanyAnnualReportChange dataCompanyAnnualReportChange = createByDataCompanyAnnualReportChangeCreateCommand(dataCompanyAnnualReportChangeCreateCommand);
		dataCompanyAnnualReportChange.setAddControl(dataCompanyAnnualReportChangeCreateCommand);
		boolean save = dataCompanyAnnualReportChangeGateway.save(dataCompanyAnnualReportChange);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportChangeAppStructMapping.instance.toDataCompanyAnnualReportChangeVO(dataCompanyAnnualReportChange));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报变更创建指令创建企业年报变更模型
	 * @param dataCompanyAnnualReportChangeCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportChange createByDataCompanyAnnualReportChangeCreateCommand(DataCompanyAnnualReportChangeCreateCommand dataCompanyAnnualReportChangeCreateCommand){
		DataCompanyAnnualReportChange dataCompanyAnnualReportChange = DataCompanyAnnualReportChange.create();
		DataCompanyAnnualReportChangeCreateCommandToDataCompanyAnnualReportChangeMapping.instance.fillDataCompanyAnnualReportChangeByDataCompanyAnnualReportChangeCreateCommand(dataCompanyAnnualReportChange, dataCompanyAnnualReportChangeCreateCommand);
		return dataCompanyAnnualReportChange;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportChangeCreateCommandToDataCompanyAnnualReportChangeMapping{
		DataCompanyAnnualReportChangeCreateCommandToDataCompanyAnnualReportChangeMapping instance = Mappers.getMapper( DataCompanyAnnualReportChangeCreateCommandToDataCompanyAnnualReportChangeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportChange
		 * @param dataCompanyAnnualReportChangeCreateCommand
		 */
		void fillDataCompanyAnnualReportChangeByDataCompanyAnnualReportChangeCreateCommand(@MappingTarget DataCompanyAnnualReportChange dataCompanyAnnualReportChange, DataCompanyAnnualReportChangeCreateCommand dataCompanyAnnualReportChangeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportChangeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportChangeGateway(DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway) {
		this.dataCompanyAnnualReportChangeGateway = dataCompanyAnnualReportChangeGateway;
	}
}

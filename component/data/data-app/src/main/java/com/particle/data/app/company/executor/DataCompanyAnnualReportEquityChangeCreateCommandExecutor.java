package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportEquityChangeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChange;
import com.particle.data.domain.company.gateway.DataCompanyAnnualReportEquityChangeGateway;
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
 * 企业年报股权变更 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:35:57
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway;

	/**
	 * 执行企业年报股权变更添加指令
	 * @param dataCompanyAnnualReportEquityChangeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeVO> execute(@Valid DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand) {
		DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange = createByDataCompanyAnnualReportEquityChangeCreateCommand(dataCompanyAnnualReportEquityChangeCreateCommand);
		dataCompanyAnnualReportEquityChange.setAddControl(dataCompanyAnnualReportEquityChangeCreateCommand);
		boolean save = dataCompanyAnnualReportEquityChangeGateway.save(dataCompanyAnnualReportEquityChange);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportEquityChangeAppStructMapping.instance.toDataCompanyAnnualReportEquityChangeVO(dataCompanyAnnualReportEquityChange));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报股权变更创建指令创建企业年报股权变更模型
	 * @param dataCompanyAnnualReportEquityChangeCreateCommand
	 * @return
	 */
	private DataCompanyAnnualReportEquityChange createByDataCompanyAnnualReportEquityChangeCreateCommand(DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand){
		DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange = DataCompanyAnnualReportEquityChange.create();
		DataCompanyAnnualReportEquityChangeCreateCommandToDataCompanyAnnualReportEquityChangeMapping.instance.fillDataCompanyAnnualReportEquityChangeByDataCompanyAnnualReportEquityChangeCreateCommand(dataCompanyAnnualReportEquityChange, dataCompanyAnnualReportEquityChangeCreateCommand);
		return dataCompanyAnnualReportEquityChange;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAnnualReportEquityChangeCreateCommandToDataCompanyAnnualReportEquityChangeMapping{
		DataCompanyAnnualReportEquityChangeCreateCommandToDataCompanyAnnualReportEquityChangeMapping instance = Mappers.getMapper( DataCompanyAnnualReportEquityChangeCreateCommandToDataCompanyAnnualReportEquityChangeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportEquityChange
		 * @param dataCompanyAnnualReportEquityChangeCreateCommand
		 */
		void fillDataCompanyAnnualReportEquityChangeByDataCompanyAnnualReportEquityChangeCreateCommand(@MappingTarget DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange, DataCompanyAnnualReportEquityChangeCreateCommand dataCompanyAnnualReportEquityChangeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAnnualReportEquityChangeGateway
	 */
	@Autowired
	public void setDataCompanyAnnualReportEquityChangeGateway(DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway) {
		this.dataCompanyAnnualReportEquityChangeGateway = dataCompanyAnnualReportEquityChangeGateway;
	}
}

package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportEquityChangeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportEquityChangeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportEquityChangeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChange;
import com.particle.data.domain.company.DataCompanyAnnualReportEquityChangeId;
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
 * 企业年报股权变更 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportEquityChangeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportEquityChangeGateway dataCompanyAnnualReportEquityChangeGateway;

	/**
	 * 执行 企业年报股权变更 更新指令
	 * @param dataCompanyAnnualReportEquityChangeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportEquityChangeVO> execute(@Valid DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand) {
		DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange = createByDataCompanyAnnualReportEquityChangeUpdateCommand(dataCompanyAnnualReportEquityChangeUpdateCommand);
		dataCompanyAnnualReportEquityChange.setUpdateControl(dataCompanyAnnualReportEquityChangeUpdateCommand);
		boolean save = dataCompanyAnnualReportEquityChangeGateway.save(dataCompanyAnnualReportEquityChange);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportEquityChangeAppStructMapping.instance.toDataCompanyAnnualReportEquityChangeVO(dataCompanyAnnualReportEquityChange));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报股权变更更新指令创建企业年报股权变更模型
	 * @param dataCompanyAnnualReportEquityChangeUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportEquityChange createByDataCompanyAnnualReportEquityChangeUpdateCommand(DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand){
		DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange = DataCompanyAnnualReportEquityChange.create();
		DataCompanyAnnualReportEquityChangeUpdateCommandToDataCompanyAnnualReportEquityChangeMapping.instance.fillDataCompanyAnnualReportEquityChangeByDataCompanyAnnualReportEquityChangeUpdateCommand(dataCompanyAnnualReportEquityChange, dataCompanyAnnualReportEquityChangeUpdateCommand);
		return dataCompanyAnnualReportEquityChange;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportEquityChangeUpdateCommandToDataCompanyAnnualReportEquityChangeMapping{
		DataCompanyAnnualReportEquityChangeUpdateCommandToDataCompanyAnnualReportEquityChangeMapping instance = Mappers.getMapper(DataCompanyAnnualReportEquityChangeUpdateCommandToDataCompanyAnnualReportEquityChangeMapping.class );

		default DataCompanyAnnualReportEquityChangeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportEquityChangeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportEquityChange
		 * @param dataCompanyAnnualReportEquityChangeUpdateCommand
		 */
		void fillDataCompanyAnnualReportEquityChangeByDataCompanyAnnualReportEquityChangeUpdateCommand(@MappingTarget DataCompanyAnnualReportEquityChange dataCompanyAnnualReportEquityChange, DataCompanyAnnualReportEquityChangeUpdateCommand dataCompanyAnnualReportEquityChangeUpdateCommand);
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

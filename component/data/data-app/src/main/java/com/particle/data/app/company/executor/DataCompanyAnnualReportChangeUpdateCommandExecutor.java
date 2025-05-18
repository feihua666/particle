package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportChangeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportChangeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportChangeVO;
import com.particle.data.domain.company.DataCompanyAnnualReportChange;
import com.particle.data.domain.company.DataCompanyAnnualReportChangeId;
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
 * 企业年报变更 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportChangeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportChangeGateway dataCompanyAnnualReportChangeGateway;

	/**
	 * 执行 企业年报变更 更新指令
	 * @param dataCompanyAnnualReportChangeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportChangeVO> execute(@Valid DataCompanyAnnualReportChangeUpdateCommand dataCompanyAnnualReportChangeUpdateCommand) {
		DataCompanyAnnualReportChange dataCompanyAnnualReportChange = createByDataCompanyAnnualReportChangeUpdateCommand(dataCompanyAnnualReportChangeUpdateCommand);
		dataCompanyAnnualReportChange.setUpdateControl(dataCompanyAnnualReportChangeUpdateCommand);
		boolean save = dataCompanyAnnualReportChangeGateway.save(dataCompanyAnnualReportChange);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportChangeAppStructMapping.instance.toDataCompanyAnnualReportChangeVO(dataCompanyAnnualReportChange));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报变更更新指令创建企业年报变更模型
	 * @param dataCompanyAnnualReportChangeUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportChange createByDataCompanyAnnualReportChangeUpdateCommand(DataCompanyAnnualReportChangeUpdateCommand dataCompanyAnnualReportChangeUpdateCommand){
		DataCompanyAnnualReportChange dataCompanyAnnualReportChange = DataCompanyAnnualReportChange.create();
		DataCompanyAnnualReportChangeUpdateCommandToDataCompanyAnnualReportChangeMapping.instance.fillDataCompanyAnnualReportChangeByDataCompanyAnnualReportChangeUpdateCommand(dataCompanyAnnualReportChange, dataCompanyAnnualReportChangeUpdateCommand);
		return dataCompanyAnnualReportChange;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportChangeUpdateCommandToDataCompanyAnnualReportChangeMapping{
		DataCompanyAnnualReportChangeUpdateCommandToDataCompanyAnnualReportChangeMapping instance = Mappers.getMapper(DataCompanyAnnualReportChangeUpdateCommandToDataCompanyAnnualReportChangeMapping.class );

		default DataCompanyAnnualReportChangeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportChangeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportChange
		 * @param dataCompanyAnnualReportChangeUpdateCommand
		 */
		void fillDataCompanyAnnualReportChangeByDataCompanyAnnualReportChangeUpdateCommand(@MappingTarget DataCompanyAnnualReportChange dataCompanyAnnualReportChange, DataCompanyAnnualReportChangeUpdateCommand dataCompanyAnnualReportChangeUpdateCommand);
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

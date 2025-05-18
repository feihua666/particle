package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAnnualReportShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAnnualReportShareholderUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAnnualReportShareholderVO;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholder;
import com.particle.data.domain.company.DataCompanyAnnualReportShareholderId;
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
 * 企业年报股东 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAnnualReportShareholderUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAnnualReportShareholderGateway dataCompanyAnnualReportShareholderGateway;

	/**
	 * 执行 企业年报股东 更新指令
	 * @param dataCompanyAnnualReportShareholderUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAnnualReportShareholderVO> execute(@Valid DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand) {
		DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder = createByDataCompanyAnnualReportShareholderUpdateCommand(dataCompanyAnnualReportShareholderUpdateCommand);
		dataCompanyAnnualReportShareholder.setUpdateControl(dataCompanyAnnualReportShareholderUpdateCommand);
		boolean save = dataCompanyAnnualReportShareholderGateway.save(dataCompanyAnnualReportShareholder);
		if (save) {
			return SingleResponse.of(DataCompanyAnnualReportShareholderAppStructMapping.instance.toDataCompanyAnnualReportShareholderVO(dataCompanyAnnualReportShareholder));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业年报股东更新指令创建企业年报股东模型
	 * @param dataCompanyAnnualReportShareholderUpdateCommand
	 * @return
	 */
	private DataCompanyAnnualReportShareholder createByDataCompanyAnnualReportShareholderUpdateCommand(DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand){
		DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder = DataCompanyAnnualReportShareholder.create();
		DataCompanyAnnualReportShareholderUpdateCommandToDataCompanyAnnualReportShareholderMapping.instance.fillDataCompanyAnnualReportShareholderByDataCompanyAnnualReportShareholderUpdateCommand(dataCompanyAnnualReportShareholder, dataCompanyAnnualReportShareholderUpdateCommand);
		return dataCompanyAnnualReportShareholder;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAnnualReportShareholderUpdateCommandToDataCompanyAnnualReportShareholderMapping{
		DataCompanyAnnualReportShareholderUpdateCommandToDataCompanyAnnualReportShareholderMapping instance = Mappers.getMapper(DataCompanyAnnualReportShareholderUpdateCommandToDataCompanyAnnualReportShareholderMapping.class );

		default DataCompanyAnnualReportShareholderId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAnnualReportShareholderId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAnnualReportShareholder
		 * @param dataCompanyAnnualReportShareholderUpdateCommand
		 */
		void fillDataCompanyAnnualReportShareholderByDataCompanyAnnualReportShareholderUpdateCommand(@MappingTarget DataCompanyAnnualReportShareholder dataCompanyAnnualReportShareholder, DataCompanyAnnualReportShareholderUpdateCommand dataCompanyAnnualReportShareholderUpdateCommand);
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

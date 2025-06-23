package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicenseVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicense;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicenseGateway;
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
 * 企业知识产权商标许可信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicenseUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway;

	/**
	 * 执行 企业知识产权商标许可信息 更新指令
	 * @param dataCompanyIprTrademarkLicenseUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicenseVO> execute(@Valid DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand) {
		DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense = createByDataCompanyIprTrademarkLicenseUpdateCommand(dataCompanyIprTrademarkLicenseUpdateCommand);
		dataCompanyIprTrademarkLicense.initForUpdate();
		dataCompanyIprTrademarkLicense.setUpdateControl(dataCompanyIprTrademarkLicenseUpdateCommand);
		boolean save = dataCompanyIprTrademarkLicenseGateway.save(dataCompanyIprTrademarkLicense);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkLicenseAppStructMapping.instance.toDataCompanyIprTrademarkLicenseVO(dataCompanyIprTrademarkLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标许可信息更新指令创建企业知识产权商标许可信息模型
	 * @param dataCompanyIprTrademarkLicenseUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkLicense createByDataCompanyIprTrademarkLicenseUpdateCommand(DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand){
		DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense = DataCompanyIprTrademarkLicense.create();
		DataCompanyIprTrademarkLicenseUpdateCommandToDataCompanyIprTrademarkLicenseMapping.instance.fillDataCompanyIprTrademarkLicenseByDataCompanyIprTrademarkLicenseUpdateCommand(dataCompanyIprTrademarkLicense, dataCompanyIprTrademarkLicenseUpdateCommand);
		return dataCompanyIprTrademarkLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkLicenseUpdateCommandToDataCompanyIprTrademarkLicenseMapping{
		DataCompanyIprTrademarkLicenseUpdateCommandToDataCompanyIprTrademarkLicenseMapping instance = Mappers.getMapper(DataCompanyIprTrademarkLicenseUpdateCommandToDataCompanyIprTrademarkLicenseMapping.class );

		default DataCompanyIprTrademarkLicenseId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkLicenseId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkLicense
		 * @param dataCompanyIprTrademarkLicenseUpdateCommand
		 */
		void fillDataCompanyIprTrademarkLicenseByDataCompanyIprTrademarkLicenseUpdateCommand(@MappingTarget DataCompanyIprTrademarkLicense dataCompanyIprTrademarkLicense, DataCompanyIprTrademarkLicenseUpdateCommand dataCompanyIprTrademarkLicenseUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicenseGateway(DataCompanyIprTrademarkLicenseGateway dataCompanyIprTrademarkLicenseGateway) {
		this.dataCompanyIprTrademarkLicenseGateway = dataCompanyIprTrademarkLicenseGateway;
	}
}

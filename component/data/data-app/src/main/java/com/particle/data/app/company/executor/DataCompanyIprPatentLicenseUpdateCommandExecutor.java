package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentLicenseVO;
import com.particle.data.domain.company.DataCompanyIprPatentLicense;
import com.particle.data.domain.company.DataCompanyIprPatentLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentLicenseGateway;
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
 * 企业知识产权专利许可信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentLicenseUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway;

	/**
	 * 执行 企业知识产权专利许可信息 更新指令
	 * @param dataCompanyIprPatentLicenseUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentLicenseVO> execute(@Valid DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand) {
		DataCompanyIprPatentLicense dataCompanyIprPatentLicense = createByDataCompanyIprPatentLicenseUpdateCommand(dataCompanyIprPatentLicenseUpdateCommand);
		dataCompanyIprPatentLicense.initForUpdate();
		dataCompanyIprPatentLicense.setUpdateControl(dataCompanyIprPatentLicenseUpdateCommand);
		boolean save = dataCompanyIprPatentLicenseGateway.save(dataCompanyIprPatentLicense);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentLicenseAppStructMapping.instance.toDataCompanyIprPatentLicenseVO(dataCompanyIprPatentLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利许可信息更新指令创建企业知识产权专利许可信息模型
	 * @param dataCompanyIprPatentLicenseUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentLicense createByDataCompanyIprPatentLicenseUpdateCommand(DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand){
		DataCompanyIprPatentLicense dataCompanyIprPatentLicense = DataCompanyIprPatentLicense.create();
		DataCompanyIprPatentLicenseUpdateCommandToDataCompanyIprPatentLicenseMapping.instance.fillDataCompanyIprPatentLicenseByDataCompanyIprPatentLicenseUpdateCommand(dataCompanyIprPatentLicense, dataCompanyIprPatentLicenseUpdateCommand);
		return dataCompanyIprPatentLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentLicenseUpdateCommandToDataCompanyIprPatentLicenseMapping{
		DataCompanyIprPatentLicenseUpdateCommandToDataCompanyIprPatentLicenseMapping instance = Mappers.getMapper(DataCompanyIprPatentLicenseUpdateCommandToDataCompanyIprPatentLicenseMapping.class );

		default DataCompanyIprPatentLicenseId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentLicenseId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentLicense
		 * @param dataCompanyIprPatentLicenseUpdateCommand
		 */
		void fillDataCompanyIprPatentLicenseByDataCompanyIprPatentLicenseUpdateCommand(@MappingTarget DataCompanyIprPatentLicense dataCompanyIprPatentLicense, DataCompanyIprPatentLicenseUpdateCommand dataCompanyIprPatentLicenseUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentLicenseGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentLicenseGateway(DataCompanyIprPatentLicenseGateway dataCompanyIprPatentLicenseGateway) {
		this.dataCompanyIprPatentLicenseGateway = dataCompanyIprPatentLicenseGateway;
	}
}

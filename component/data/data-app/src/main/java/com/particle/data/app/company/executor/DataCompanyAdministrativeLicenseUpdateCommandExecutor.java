package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAdministrativeLicenseAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAdministrativeLicenseUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAdministrativeLicenseVO;
import com.particle.data.domain.company.DataCompanyAdministrativeLicense;
import com.particle.data.domain.company.DataCompanyAdministrativeLicenseId;
import com.particle.data.domain.company.gateway.DataCompanyAdministrativeLicenseGateway;
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
 * 企业行政许可 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyAdministrativeLicenseUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway;

	/**
	 * 执行 企业行政许可 更新指令
	 * @param dataCompanyAdministrativeLicenseUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAdministrativeLicenseVO> execute(@Valid DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand) {
		DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense = createByDataCompanyAdministrativeLicenseUpdateCommand(dataCompanyAdministrativeLicenseUpdateCommand);
		dataCompanyAdministrativeLicense.initForUpdate();
		dataCompanyAdministrativeLicense.setUpdateControl(dataCompanyAdministrativeLicenseUpdateCommand);
		boolean save = dataCompanyAdministrativeLicenseGateway.save(dataCompanyAdministrativeLicense);
		if (save) {
			return SingleResponse.of(DataCompanyAdministrativeLicenseAppStructMapping.instance.toDataCompanyAdministrativeLicenseVO(dataCompanyAdministrativeLicense));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业行政许可更新指令创建企业行政许可模型
	 * @param dataCompanyAdministrativeLicenseUpdateCommand
	 * @return
	 */
	private DataCompanyAdministrativeLicense createByDataCompanyAdministrativeLicenseUpdateCommand(DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand){
		DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense = DataCompanyAdministrativeLicense.create();
		DataCompanyAdministrativeLicenseUpdateCommandToDataCompanyAdministrativeLicenseMapping.instance.fillDataCompanyAdministrativeLicenseByDataCompanyAdministrativeLicenseUpdateCommand(dataCompanyAdministrativeLicense, dataCompanyAdministrativeLicenseUpdateCommand);
		return dataCompanyAdministrativeLicense;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyAdministrativeLicenseUpdateCommandToDataCompanyAdministrativeLicenseMapping{
		DataCompanyAdministrativeLicenseUpdateCommandToDataCompanyAdministrativeLicenseMapping instance = Mappers.getMapper(DataCompanyAdministrativeLicenseUpdateCommandToDataCompanyAdministrativeLicenseMapping.class );

		default DataCompanyAdministrativeLicenseId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyAdministrativeLicenseId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAdministrativeLicense
		 * @param dataCompanyAdministrativeLicenseUpdateCommand
		 */
		void fillDataCompanyAdministrativeLicenseByDataCompanyAdministrativeLicenseUpdateCommand(@MappingTarget DataCompanyAdministrativeLicense dataCompanyAdministrativeLicense, DataCompanyAdministrativeLicenseUpdateCommand dataCompanyAdministrativeLicenseUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAdministrativeLicenseGateway
	 */
	@Autowired
	public void setDataCompanyAdministrativeLicenseGateway(DataCompanyAdministrativeLicenseGateway dataCompanyAdministrativeLicenseGateway) {
		this.dataCompanyAdministrativeLicenseGateway = dataCompanyAdministrativeLicenseGateway;
	}
}

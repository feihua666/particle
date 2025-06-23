package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkLicensePersonAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkLicensePersonUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkLicensePersonVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePerson;
import com.particle.data.domain.company.DataCompanyIprTrademarkLicensePersonId;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkLicensePersonGateway;
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
 * 企业知识产权商标许可人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkLicensePersonUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway;

	/**
	 * 执行 企业知识产权商标许可人 更新指令
	 * @param dataCompanyIprTrademarkLicensePersonUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkLicensePersonVO> execute(@Valid DataCompanyIprTrademarkLicensePersonUpdateCommand dataCompanyIprTrademarkLicensePersonUpdateCommand) {
		DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson = createByDataCompanyIprTrademarkLicensePersonUpdateCommand(dataCompanyIprTrademarkLicensePersonUpdateCommand);
		dataCompanyIprTrademarkLicensePerson.initForUpdate();
		dataCompanyIprTrademarkLicensePerson.setUpdateControl(dataCompanyIprTrademarkLicensePersonUpdateCommand);
		boolean save = dataCompanyIprTrademarkLicensePersonGateway.save(dataCompanyIprTrademarkLicensePerson);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkLicensePersonAppStructMapping.instance.toDataCompanyIprTrademarkLicensePersonVO(dataCompanyIprTrademarkLicensePerson));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标许可人更新指令创建企业知识产权商标许可人模型
	 * @param dataCompanyIprTrademarkLicensePersonUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkLicensePerson createByDataCompanyIprTrademarkLicensePersonUpdateCommand(DataCompanyIprTrademarkLicensePersonUpdateCommand dataCompanyIprTrademarkLicensePersonUpdateCommand){
		DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson = DataCompanyIprTrademarkLicensePerson.create();
		DataCompanyIprTrademarkLicensePersonUpdateCommandToDataCompanyIprTrademarkLicensePersonMapping.instance.fillDataCompanyIprTrademarkLicensePersonByDataCompanyIprTrademarkLicensePersonUpdateCommand(dataCompanyIprTrademarkLicensePerson, dataCompanyIprTrademarkLicensePersonUpdateCommand);
		return dataCompanyIprTrademarkLicensePerson;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkLicensePersonUpdateCommandToDataCompanyIprTrademarkLicensePersonMapping{
		DataCompanyIprTrademarkLicensePersonUpdateCommandToDataCompanyIprTrademarkLicensePersonMapping instance = Mappers.getMapper(DataCompanyIprTrademarkLicensePersonUpdateCommandToDataCompanyIprTrademarkLicensePersonMapping.class );

		default DataCompanyIprTrademarkLicensePersonId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkLicensePersonId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkLicensePerson
		 * @param dataCompanyIprTrademarkLicensePersonUpdateCommand
		 */
		void fillDataCompanyIprTrademarkLicensePersonByDataCompanyIprTrademarkLicensePersonUpdateCommand(@MappingTarget DataCompanyIprTrademarkLicensePerson dataCompanyIprTrademarkLicensePerson, DataCompanyIprTrademarkLicensePersonUpdateCommand dataCompanyIprTrademarkLicensePersonUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkLicensePersonGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkLicensePersonGateway(DataCompanyIprTrademarkLicensePersonGateway dataCompanyIprTrademarkLicensePersonGateway) {
		this.dataCompanyIprTrademarkLicensePersonGateway = dataCompanyIprTrademarkLicensePersonGateway;
	}
}

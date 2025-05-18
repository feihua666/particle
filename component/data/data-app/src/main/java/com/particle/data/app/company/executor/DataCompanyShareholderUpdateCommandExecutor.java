package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyShareholderAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyShareholderUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyShareholderVO;
import com.particle.data.domain.company.DataCompanyShareholder;
import com.particle.data.domain.company.DataCompanyShareholderId;
import com.particle.data.domain.company.gateway.DataCompanyShareholderGateway;
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
 * 企业股东 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyShareholderUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyShareholderGateway dataCompanyShareholderGateway;

	/**
	 * 执行 企业股东 更新指令
	 * @param dataCompanyShareholderUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyShareholderVO> execute(@Valid DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand) {
		DataCompanyShareholder dataCompanyShareholder = createByDataCompanyShareholderUpdateCommand(dataCompanyShareholderUpdateCommand);
		dataCompanyShareholder.setUpdateControl(dataCompanyShareholderUpdateCommand);
		dataCompanyShareholder.initForUpdate();
		boolean save = dataCompanyShareholderGateway.save(dataCompanyShareholder);
		if (save) {
			return SingleResponse.of(DataCompanyShareholderAppStructMapping.instance.toDataCompanyShareholderVO(dataCompanyShareholder));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业股东更新指令创建企业股东模型
	 * @param dataCompanyShareholderUpdateCommand
	 * @return
	 */
	private DataCompanyShareholder createByDataCompanyShareholderUpdateCommand(DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand){
		DataCompanyShareholder dataCompanyShareholder = DataCompanyShareholder.create();
		DataCompanyShareholderUpdateCommandToDataCompanyShareholderMapping.instance.fillDataCompanyShareholderByDataCompanyShareholderUpdateCommand(dataCompanyShareholder, dataCompanyShareholderUpdateCommand);
		return dataCompanyShareholder;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyShareholderUpdateCommandToDataCompanyShareholderMapping{
		DataCompanyShareholderUpdateCommandToDataCompanyShareholderMapping instance = Mappers.getMapper(DataCompanyShareholderUpdateCommandToDataCompanyShareholderMapping.class );

		default DataCompanyShareholderId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyShareholderId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyShareholder
		 * @param dataCompanyShareholderUpdateCommand
		 */
		void fillDataCompanyShareholderByDataCompanyShareholderUpdateCommand(@MappingTarget DataCompanyShareholder dataCompanyShareholder, DataCompanyShareholderUpdateCommand dataCompanyShareholderUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyShareholderGateway
	 */
	@Autowired
	public void setDataCompanyShareholderGateway(DataCompanyShareholderGateway dataCompanyShareholderGateway) {
		this.dataCompanyShareholderGateway = dataCompanyShareholderGateway;
	}
}

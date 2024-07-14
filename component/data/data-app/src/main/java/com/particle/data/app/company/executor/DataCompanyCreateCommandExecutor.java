package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyVO;
import com.particle.data.domain.company.DataCompany;
import com.particle.data.domain.company.gateway.DataCompanyGateway;
import com.particle.global.dto.response.SingleResponse;
import com.particle.global.exception.code.ErrorCodeGlobalEnum;
import com.particle.common.app.executor.AbstractBaseExecutor;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;

/**
 * <p>
 * 企业 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2024-07-14 11:23:44
 */
@Component
@Validated
public class DataCompanyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyGateway dataCompanyGateway;

	/**
	 * 执行企业添加指令
	 * @param dataCompanyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyVO> execute(@Valid DataCompanyCreateCommand dataCompanyCreateCommand) {
		DataCompany dataCompany = createByDataCompanyCreateCommand(dataCompanyCreateCommand);
		dataCompany.setAddControl(dataCompanyCreateCommand);
		boolean save = dataCompanyGateway.save(dataCompany);
		if (save) {
			return SingleResponse.of(DataCompanyAppStructMapping.instance.toDataCompanyVO(dataCompany));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业创建指令创建企业模型
	 * @param dataCompanyCreateCommand
	 * @return
	 */
	private DataCompany createByDataCompanyCreateCommand(DataCompanyCreateCommand dataCompanyCreateCommand){
		DataCompany dataCompany = DataCompany.create();
		DataCompanyCreateCommandToDataCompanyMapping.instance.fillDataCompanyByDataCompanyCreateCommand(dataCompany, dataCompanyCreateCommand);
		return dataCompany;
	}

	@Mapper
	interface  DataCompanyCreateCommandToDataCompanyMapping{
		DataCompanyCreateCommandToDataCompanyMapping instance = Mappers.getMapper( DataCompanyCreateCommandToDataCompanyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompany
		 * @param dataCompanyCreateCommand
		 */
		void fillDataCompanyByDataCompanyCreateCommand(@MappingTarget DataCompany dataCompany, DataCompanyCreateCommand dataCompanyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyGateway
	 */
	@Autowired
	public void setDataCompanyGateway(DataCompanyGateway dataCompanyGateway) {
		this.dataCompanyGateway = dataCompanyGateway;
	}
}

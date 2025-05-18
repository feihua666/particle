package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentFamilyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.domain.company.DataCompanyIprPatentFamily;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentFamilyGateway;
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
 * 企业知识产权专利同族信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:43
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway;

	/**
	 * 执行企业知识产权专利同族信息添加指令
	 * @param dataCompanyIprPatentFamilyCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyVO> execute(@Valid DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand) {
		DataCompanyIprPatentFamily dataCompanyIprPatentFamily = createByDataCompanyIprPatentFamilyCreateCommand(dataCompanyIprPatentFamilyCreateCommand);
		dataCompanyIprPatentFamily.initForAdd();
		dataCompanyIprPatentFamily.setAddControl(dataCompanyIprPatentFamilyCreateCommand);
		boolean save = dataCompanyIprPatentFamilyGateway.save(dataCompanyIprPatentFamily);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentFamilyAppStructMapping.instance.toDataCompanyIprPatentFamilyVO(dataCompanyIprPatentFamily));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利同族信息创建指令创建企业知识产权专利同族信息模型
	 * @param dataCompanyIprPatentFamilyCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentFamily createByDataCompanyIprPatentFamilyCreateCommand(DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand){
		DataCompanyIprPatentFamily dataCompanyIprPatentFamily = DataCompanyIprPatentFamily.create();
		DataCompanyIprPatentFamilyCreateCommandToDataCompanyIprPatentFamilyMapping.instance.fillDataCompanyIprPatentFamilyByDataCompanyIprPatentFamilyCreateCommand(dataCompanyIprPatentFamily, dataCompanyIprPatentFamilyCreateCommand);
		return dataCompanyIprPatentFamily;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentFamilyCreateCommandToDataCompanyIprPatentFamilyMapping{
		DataCompanyIprPatentFamilyCreateCommandToDataCompanyIprPatentFamilyMapping instance = Mappers.getMapper( DataCompanyIprPatentFamilyCreateCommandToDataCompanyIprPatentFamilyMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentFamily
		 * @param dataCompanyIprPatentFamilyCreateCommand
		 */
		void fillDataCompanyIprPatentFamilyByDataCompanyIprPatentFamilyCreateCommand(@MappingTarget DataCompanyIprPatentFamily dataCompanyIprPatentFamily, DataCompanyIprPatentFamilyCreateCommand dataCompanyIprPatentFamilyCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentFamilyGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentFamilyGateway(DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway) {
		this.dataCompanyIprPatentFamilyGateway = dataCompanyIprPatentFamilyGateway;
	}
}

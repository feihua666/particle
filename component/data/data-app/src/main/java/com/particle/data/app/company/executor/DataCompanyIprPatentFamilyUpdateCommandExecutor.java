package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentFamilyAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentFamilyUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentFamilyVO;
import com.particle.data.domain.company.DataCompanyIprPatentFamily;
import com.particle.data.domain.company.DataCompanyIprPatentFamilyId;
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
 * 企业知识产权专利同族信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentFamilyUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentFamilyGateway dataCompanyIprPatentFamilyGateway;

	/**
	 * 执行 企业知识产权专利同族信息 更新指令
	 * @param dataCompanyIprPatentFamilyUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentFamilyVO> execute(@Valid DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand) {
		DataCompanyIprPatentFamily dataCompanyIprPatentFamily = createByDataCompanyIprPatentFamilyUpdateCommand(dataCompanyIprPatentFamilyUpdateCommand);
		dataCompanyIprPatentFamily.initForUpdate();
		dataCompanyIprPatentFamily.setUpdateControl(dataCompanyIprPatentFamilyUpdateCommand);
		boolean save = dataCompanyIprPatentFamilyGateway.save(dataCompanyIprPatentFamily);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentFamilyAppStructMapping.instance.toDataCompanyIprPatentFamilyVO(dataCompanyIprPatentFamily));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利同族信息更新指令创建企业知识产权专利同族信息模型
	 * @param dataCompanyIprPatentFamilyUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentFamily createByDataCompanyIprPatentFamilyUpdateCommand(DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand){
		DataCompanyIprPatentFamily dataCompanyIprPatentFamily = DataCompanyIprPatentFamily.create();
		DataCompanyIprPatentFamilyUpdateCommandToDataCompanyIprPatentFamilyMapping.instance.fillDataCompanyIprPatentFamilyByDataCompanyIprPatentFamilyUpdateCommand(dataCompanyIprPatentFamily, dataCompanyIprPatentFamilyUpdateCommand);
		return dataCompanyIprPatentFamily;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentFamilyUpdateCommandToDataCompanyIprPatentFamilyMapping{
		DataCompanyIprPatentFamilyUpdateCommandToDataCompanyIprPatentFamilyMapping instance = Mappers.getMapper(DataCompanyIprPatentFamilyUpdateCommandToDataCompanyIprPatentFamilyMapping.class );

		default DataCompanyIprPatentFamilyId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentFamilyId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentFamily
		 * @param dataCompanyIprPatentFamilyUpdateCommand
		 */
		void fillDataCompanyIprPatentFamilyByDataCompanyIprPatentFamilyUpdateCommand(@MappingTarget DataCompanyIprPatentFamily dataCompanyIprPatentFamily, DataCompanyIprPatentFamilyUpdateCommand dataCompanyIprPatentFamilyUpdateCommand);
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

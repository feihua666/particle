package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyBasicAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyBasicCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.domain.company.DataCompanyBasic;
import com.particle.data.domain.company.gateway.DataCompanyBasicGateway;
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
 * 企业基本信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:34:19
 */
@Component
@Validated
public class DataCompanyBasicCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyBasicGateway dataCompanyBasicGateway;

	/**
	 * 执行企业基本信息添加指令
	 * @param dataCompanyBasicCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicVO> execute(@Valid DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand) {
		DataCompanyBasic dataCompanyBasic = createByDataCompanyBasicCreateCommand(dataCompanyBasicCreateCommand);
		dataCompanyBasic.setAddControl(dataCompanyBasicCreateCommand);
		boolean save = dataCompanyBasicGateway.save(dataCompanyBasic);
		if (save) {
			return SingleResponse.of(DataCompanyBasicAppStructMapping.instance.toDataCompanyBasicVO(dataCompanyBasic));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业基本信息创建指令创建企业基本信息模型
	 * @param dataCompanyBasicCreateCommand
	 * @return
	 */
	private DataCompanyBasic createByDataCompanyBasicCreateCommand(DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand){
		DataCompanyBasic dataCompanyBasic = DataCompanyBasic.create();
		DataCompanyBasicCreateCommandToDataCompanyBasicMapping.instance.fillDataCompanyBasicByDataCompanyBasicCreateCommand(dataCompanyBasic, dataCompanyBasicCreateCommand);
		return dataCompanyBasic;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyBasicCreateCommandToDataCompanyBasicMapping{
		DataCompanyBasicCreateCommandToDataCompanyBasicMapping instance = Mappers.getMapper( DataCompanyBasicCreateCommandToDataCompanyBasicMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyBasic
		 * @param dataCompanyBasicCreateCommand
		 */
		void fillDataCompanyBasicByDataCompanyBasicCreateCommand(@MappingTarget DataCompanyBasic dataCompanyBasic, DataCompanyBasicCreateCommand dataCompanyBasicCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyBasicGateway
	 */
	@Autowired
	public void setDataCompanyBasicGateway(DataCompanyBasicGateway dataCompanyBasicGateway) {
		this.dataCompanyBasicGateway = dataCompanyBasicGateway;
	}
}

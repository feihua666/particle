package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.domain.company.DataCompanyIprPatent;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentGateway;
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
 * 企业知识产权专利 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:39:48
 */
@Component
@Validated
public class DataCompanyIprPatentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentGateway dataCompanyIprPatentGateway;

	/**
	 * 执行企业知识产权专利添加指令
	 * @param dataCompanyIprPatentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentVO> execute(@Valid DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand) {
		DataCompanyIprPatent dataCompanyIprPatent = createByDataCompanyIprPatentCreateCommand(dataCompanyIprPatentCreateCommand);
		dataCompanyIprPatent.initForAdd();
		dataCompanyIprPatent.setAddControl(dataCompanyIprPatentCreateCommand);
		boolean save = dataCompanyIprPatentGateway.save(dataCompanyIprPatent);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentAppStructMapping.instance.toDataCompanyIprPatentVO(dataCompanyIprPatent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利创建指令创建企业知识产权专利模型
	 * @param dataCompanyIprPatentCreateCommand
	 * @return
	 */
	private DataCompanyIprPatent createByDataCompanyIprPatentCreateCommand(DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand){
		DataCompanyIprPatent dataCompanyIprPatent = DataCompanyIprPatent.create();
		DataCompanyIprPatentCreateCommandToDataCompanyIprPatentMapping.instance.fillDataCompanyIprPatentByDataCompanyIprPatentCreateCommand(dataCompanyIprPatent, dataCompanyIprPatentCreateCommand);
		return dataCompanyIprPatent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentCreateCommandToDataCompanyIprPatentMapping{
		DataCompanyIprPatentCreateCommandToDataCompanyIprPatentMapping instance = Mappers.getMapper( DataCompanyIprPatentCreateCommandToDataCompanyIprPatentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatent
		 * @param dataCompanyIprPatentCreateCommand
		 */
		void fillDataCompanyIprPatentByDataCompanyIprPatentCreateCommand(@MappingTarget DataCompanyIprPatent dataCompanyIprPatent, DataCompanyIprPatentCreateCommand dataCompanyIprPatentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentGateway(DataCompanyIprPatentGateway dataCompanyIprPatentGateway) {
		this.dataCompanyIprPatentGateway = dataCompanyIprPatentGateway;
	}
}

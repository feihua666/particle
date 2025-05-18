package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.domain.company.DataCompanyIprPatentContent;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentContentGateway;
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
 * 企业知识产权专利内容 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:40:27
 */
@Component
@Validated
public class DataCompanyIprPatentContentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway;

	/**
	 * 执行企业知识产权专利内容添加指令
	 * @param dataCompanyIprPatentContentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentVO> execute(@Valid DataCompanyIprPatentContentCreateCommand dataCompanyIprPatentContentCreateCommand) {
		DataCompanyIprPatentContent dataCompanyIprPatentContent = createByDataCompanyIprPatentContentCreateCommand(dataCompanyIprPatentContentCreateCommand);
		dataCompanyIprPatentContent.initForAdd();
		dataCompanyIprPatentContent.setAddControl(dataCompanyIprPatentContentCreateCommand);
		boolean save = dataCompanyIprPatentContentGateway.save(dataCompanyIprPatentContent);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentContentAppStructMapping.instance.toDataCompanyIprPatentContentVO(dataCompanyIprPatentContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利内容创建指令创建企业知识产权专利内容模型
	 * @param dataCompanyIprPatentContentCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentContent createByDataCompanyIprPatentContentCreateCommand(DataCompanyIprPatentContentCreateCommand dataCompanyIprPatentContentCreateCommand){
		DataCompanyIprPatentContent dataCompanyIprPatentContent = DataCompanyIprPatentContent.create();
		DataCompanyIprPatentContentCreateCommandToDataCompanyIprPatentContentMapping.instance.fillDataCompanyIprPatentContentByDataCompanyIprPatentContentCreateCommand(dataCompanyIprPatentContent, dataCompanyIprPatentContentCreateCommand);
		return dataCompanyIprPatentContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentContentCreateCommandToDataCompanyIprPatentContentMapping{
		DataCompanyIprPatentContentCreateCommandToDataCompanyIprPatentContentMapping instance = Mappers.getMapper( DataCompanyIprPatentContentCreateCommandToDataCompanyIprPatentContentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentContent
		 * @param dataCompanyIprPatentContentCreateCommand
		 */
		void fillDataCompanyIprPatentContentByDataCompanyIprPatentContentCreateCommand(@MappingTarget DataCompanyIprPatentContent dataCompanyIprPatentContent, DataCompanyIprPatentContentCreateCommand dataCompanyIprPatentContentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentContentGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentContentGateway(DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway) {
		this.dataCompanyIprPatentContentGateway = dataCompanyIprPatentContentGateway;
	}
}

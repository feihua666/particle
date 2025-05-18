package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentContentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentContentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentContentVO;
import com.particle.data.domain.company.DataCompanyIprPatentContent;
import com.particle.data.domain.company.DataCompanyIprPatentContentId;
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
 * 企业知识产权专利内容 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentContentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentContentGateway dataCompanyIprPatentContentGateway;

	/**
	 * 执行 企业知识产权专利内容 更新指令
	 * @param dataCompanyIprPatentContentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentContentVO> execute(@Valid DataCompanyIprPatentContentUpdateCommand dataCompanyIprPatentContentUpdateCommand) {
		DataCompanyIprPatentContent dataCompanyIprPatentContent = createByDataCompanyIprPatentContentUpdateCommand(dataCompanyIprPatentContentUpdateCommand);
		dataCompanyIprPatentContent.initForUpdate();
		dataCompanyIprPatentContent.setUpdateControl(dataCompanyIprPatentContentUpdateCommand);
		boolean save = dataCompanyIprPatentContentGateway.save(dataCompanyIprPatentContent);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentContentAppStructMapping.instance.toDataCompanyIprPatentContentVO(dataCompanyIprPatentContent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利内容更新指令创建企业知识产权专利内容模型
	 * @param dataCompanyIprPatentContentUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentContent createByDataCompanyIprPatentContentUpdateCommand(DataCompanyIprPatentContentUpdateCommand dataCompanyIprPatentContentUpdateCommand){
		DataCompanyIprPatentContent dataCompanyIprPatentContent = DataCompanyIprPatentContent.create();
		DataCompanyIprPatentContentUpdateCommandToDataCompanyIprPatentContentMapping.instance.fillDataCompanyIprPatentContentByDataCompanyIprPatentContentUpdateCommand(dataCompanyIprPatentContent, dataCompanyIprPatentContentUpdateCommand);
		return dataCompanyIprPatentContent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentContentUpdateCommandToDataCompanyIprPatentContentMapping{
		DataCompanyIprPatentContentUpdateCommandToDataCompanyIprPatentContentMapping instance = Mappers.getMapper(DataCompanyIprPatentContentUpdateCommandToDataCompanyIprPatentContentMapping.class );

		default DataCompanyIprPatentContentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentContentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentContent
		 * @param dataCompanyIprPatentContentUpdateCommand
		 */
		void fillDataCompanyIprPatentContentByDataCompanyIprPatentContentUpdateCommand(@MappingTarget DataCompanyIprPatentContent dataCompanyIprPatentContent, DataCompanyIprPatentContentUpdateCommand dataCompanyIprPatentContentUpdateCommand);
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

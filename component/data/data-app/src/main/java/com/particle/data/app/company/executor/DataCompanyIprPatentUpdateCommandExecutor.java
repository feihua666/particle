package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentVO;
import com.particle.data.domain.company.DataCompanyIprPatent;
import com.particle.data.domain.company.DataCompanyIprPatentId;
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
 * 企业知识产权专利 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentGateway dataCompanyIprPatentGateway;

	/**
	 * 执行 企业知识产权专利 更新指令
	 * @param dataCompanyIprPatentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentVO> execute(@Valid DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand) {
		DataCompanyIprPatent dataCompanyIprPatent = createByDataCompanyIprPatentUpdateCommand(dataCompanyIprPatentUpdateCommand);
		dataCompanyIprPatent.initForUpdate();
		dataCompanyIprPatent.setUpdateControl(dataCompanyIprPatentUpdateCommand);
		boolean save = dataCompanyIprPatentGateway.save(dataCompanyIprPatent);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentAppStructMapping.instance.toDataCompanyIprPatentVO(dataCompanyIprPatent));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利更新指令创建企业知识产权专利模型
	 * @param dataCompanyIprPatentUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatent createByDataCompanyIprPatentUpdateCommand(DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand){
		DataCompanyIprPatent dataCompanyIprPatent = DataCompanyIprPatent.create();
		DataCompanyIprPatentUpdateCommandToDataCompanyIprPatentMapping.instance.fillDataCompanyIprPatentByDataCompanyIprPatentUpdateCommand(dataCompanyIprPatent, dataCompanyIprPatentUpdateCommand);
		return dataCompanyIprPatent;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentUpdateCommandToDataCompanyIprPatentMapping{
		DataCompanyIprPatentUpdateCommandToDataCompanyIprPatentMapping instance = Mappers.getMapper(DataCompanyIprPatentUpdateCommandToDataCompanyIprPatentMapping.class );

		default DataCompanyIprPatentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatent
		 * @param dataCompanyIprPatentUpdateCommand
		 */
		void fillDataCompanyIprPatentByDataCompanyIprPatentUpdateCommand(@MappingTarget DataCompanyIprPatent dataCompanyIprPatent, DataCompanyIprPatentUpdateCommand dataCompanyIprPatentUpdateCommand);
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

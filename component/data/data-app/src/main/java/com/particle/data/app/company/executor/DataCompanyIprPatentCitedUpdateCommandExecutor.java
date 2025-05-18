package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentCitedAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentCitedUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentCitedVO;
import com.particle.data.domain.company.DataCompanyIprPatentCited;
import com.particle.data.domain.company.DataCompanyIprPatentCitedId;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentCitedGateway;
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
 * 企业知识产权专利被引证信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentCitedUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway;

	/**
	 * 执行 企业知识产权专利被引证信息 更新指令
	 * @param dataCompanyIprPatentCitedUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentCitedVO> execute(@Valid DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand) {
		DataCompanyIprPatentCited dataCompanyIprPatentCited = createByDataCompanyIprPatentCitedUpdateCommand(dataCompanyIprPatentCitedUpdateCommand);
		dataCompanyIprPatentCited.initForUpdate();
		dataCompanyIprPatentCited.setUpdateControl(dataCompanyIprPatentCitedUpdateCommand);
		boolean save = dataCompanyIprPatentCitedGateway.save(dataCompanyIprPatentCited);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentCitedAppStructMapping.instance.toDataCompanyIprPatentCitedVO(dataCompanyIprPatentCited));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利被引证信息更新指令创建企业知识产权专利被引证信息模型
	 * @param dataCompanyIprPatentCitedUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentCited createByDataCompanyIprPatentCitedUpdateCommand(DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand){
		DataCompanyIprPatentCited dataCompanyIprPatentCited = DataCompanyIprPatentCited.create();
		DataCompanyIprPatentCitedUpdateCommandToDataCompanyIprPatentCitedMapping.instance.fillDataCompanyIprPatentCitedByDataCompanyIprPatentCitedUpdateCommand(dataCompanyIprPatentCited, dataCompanyIprPatentCitedUpdateCommand);
		return dataCompanyIprPatentCited;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentCitedUpdateCommandToDataCompanyIprPatentCitedMapping{
		DataCompanyIprPatentCitedUpdateCommandToDataCompanyIprPatentCitedMapping instance = Mappers.getMapper(DataCompanyIprPatentCitedUpdateCommandToDataCompanyIprPatentCitedMapping.class );

		default DataCompanyIprPatentCitedId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentCitedId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentCited
		 * @param dataCompanyIprPatentCitedUpdateCommand
		 */
		void fillDataCompanyIprPatentCitedByDataCompanyIprPatentCitedUpdateCommand(@MappingTarget DataCompanyIprPatentCited dataCompanyIprPatentCited, DataCompanyIprPatentCitedUpdateCommand dataCompanyIprPatentCitedUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentCitedGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentCitedGateway(DataCompanyIprPatentCitedGateway dataCompanyIprPatentCitedGateway) {
		this.dataCompanyIprPatentCitedGateway = dataCompanyIprPatentCitedGateway;
	}
}

package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyBasicAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyBasicUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyBasicVO;
import com.particle.data.domain.company.DataCompanyBasic;
import com.particle.data.domain.company.DataCompanyBasicId;
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
 * 企业基本信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyBasicUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyBasicGateway dataCompanyBasicGateway;

	/**
	 * 执行 企业基本信息 更新指令
	 * @param dataCompanyBasicUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyBasicVO> execute(@Valid DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand) {
		DataCompanyBasic dataCompanyBasic = createByDataCompanyBasicUpdateCommand(dataCompanyBasicUpdateCommand);
		dataCompanyBasic.setUpdateControl(dataCompanyBasicUpdateCommand);
		boolean save = dataCompanyBasicGateway.save(dataCompanyBasic);
		if (save) {
			return SingleResponse.of(DataCompanyBasicAppStructMapping.instance.toDataCompanyBasicVO(dataCompanyBasic));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业基本信息更新指令创建企业基本信息模型
	 * @param dataCompanyBasicUpdateCommand
	 * @return
	 */
	private DataCompanyBasic createByDataCompanyBasicUpdateCommand(DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand){
		DataCompanyBasic dataCompanyBasic = DataCompanyBasic.create();
		DataCompanyBasicUpdateCommandToDataCompanyBasicMapping.instance.fillDataCompanyBasicByDataCompanyBasicUpdateCommand(dataCompanyBasic, dataCompanyBasicUpdateCommand);
		return dataCompanyBasic;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyBasicUpdateCommandToDataCompanyBasicMapping{
		DataCompanyBasicUpdateCommandToDataCompanyBasicMapping instance = Mappers.getMapper(DataCompanyBasicUpdateCommandToDataCompanyBasicMapping.class );

		default DataCompanyBasicId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyBasicId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyBasic
		 * @param dataCompanyBasicUpdateCommand
		 */
		void fillDataCompanyBasicByDataCompanyBasicUpdateCommand(@MappingTarget DataCompanyBasic dataCompanyBasic, DataCompanyBasicUpdateCommand dataCompanyBasicUpdateCommand);
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

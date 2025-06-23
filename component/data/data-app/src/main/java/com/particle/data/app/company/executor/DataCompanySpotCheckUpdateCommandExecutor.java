package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanySpotCheckAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanySpotCheckUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanySpotCheckVO;
import com.particle.data.domain.company.DataCompanySpotCheck;
import com.particle.data.domain.company.DataCompanySpotCheckId;
import com.particle.data.domain.company.gateway.DataCompanySpotCheckGateway;
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
 * 企业抽查检查 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanySpotCheckUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanySpotCheckGateway dataCompanySpotCheckGateway;

	/**
	 * 执行 企业抽查检查 更新指令
	 * @param dataCompanySpotCheckUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanySpotCheckVO> execute(@Valid DataCompanySpotCheckUpdateCommand dataCompanySpotCheckUpdateCommand) {
		DataCompanySpotCheck dataCompanySpotCheck = createByDataCompanySpotCheckUpdateCommand(dataCompanySpotCheckUpdateCommand);
		dataCompanySpotCheck.initForUpdate();
		dataCompanySpotCheck.setUpdateControl(dataCompanySpotCheckUpdateCommand);
		boolean save = dataCompanySpotCheckGateway.save(dataCompanySpotCheck);
		if (save) {
			return SingleResponse.of(DataCompanySpotCheckAppStructMapping.instance.toDataCompanySpotCheckVO(dataCompanySpotCheck));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业抽查检查更新指令创建企业抽查检查模型
	 * @param dataCompanySpotCheckUpdateCommand
	 * @return
	 */
	private DataCompanySpotCheck createByDataCompanySpotCheckUpdateCommand(DataCompanySpotCheckUpdateCommand dataCompanySpotCheckUpdateCommand){
		DataCompanySpotCheck dataCompanySpotCheck = DataCompanySpotCheck.create();
		DataCompanySpotCheckUpdateCommandToDataCompanySpotCheckMapping.instance.fillDataCompanySpotCheckByDataCompanySpotCheckUpdateCommand(dataCompanySpotCheck, dataCompanySpotCheckUpdateCommand);
		return dataCompanySpotCheck;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanySpotCheckUpdateCommandToDataCompanySpotCheckMapping{
		DataCompanySpotCheckUpdateCommandToDataCompanySpotCheckMapping instance = Mappers.getMapper(DataCompanySpotCheckUpdateCommandToDataCompanySpotCheckMapping.class );

		default DataCompanySpotCheckId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanySpotCheckId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanySpotCheck
		 * @param dataCompanySpotCheckUpdateCommand
		 */
		void fillDataCompanySpotCheckByDataCompanySpotCheckUpdateCommand(@MappingTarget DataCompanySpotCheck dataCompanySpotCheck, DataCompanySpotCheckUpdateCommand dataCompanySpotCheckUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanySpotCheckGateway
	 */
	@Autowired
	public void setDataCompanySpotCheckGateway(DataCompanySpotCheckGateway dataCompanySpotCheckGateway) {
		this.dataCompanySpotCheckGateway = dataCompanySpotCheckGateway;
	}
}

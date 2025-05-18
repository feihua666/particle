package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPunishmentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.domain.company.DataCompanyPunishment;
import com.particle.data.domain.company.gateway.DataCompanyPunishmentGateway;
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
 * 企业行政处罚 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:43:37
 */
@Component
@Validated
public class DataCompanyPunishmentCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPunishmentGateway dataCompanyPunishmentGateway;

	/**
	 * 执行企业行政处罚添加指令
	 * @param dataCompanyPunishmentCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentVO> execute(@Valid DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand) {
		DataCompanyPunishment dataCompanyPunishment = createByDataCompanyPunishmentCreateCommand(dataCompanyPunishmentCreateCommand);
		dataCompanyPunishment.setAddControl(dataCompanyPunishmentCreateCommand);
		dataCompanyPunishment.initForAdd();
		boolean save = dataCompanyPunishmentGateway.save(dataCompanyPunishment);
		if (save) {
			return SingleResponse.of(DataCompanyPunishmentAppStructMapping.instance.toDataCompanyPunishmentVO(dataCompanyPunishment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业行政处罚创建指令创建企业行政处罚模型
	 * @param dataCompanyPunishmentCreateCommand
	 * @return
	 */
	private DataCompanyPunishment createByDataCompanyPunishmentCreateCommand(DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand){
		DataCompanyPunishment dataCompanyPunishment = DataCompanyPunishment.create();
		DataCompanyPunishmentCreateCommandToDataCompanyPunishmentMapping.instance.fillDataCompanyPunishmentByDataCompanyPunishmentCreateCommand(dataCompanyPunishment, dataCompanyPunishmentCreateCommand);
		return dataCompanyPunishment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyPunishmentCreateCommandToDataCompanyPunishmentMapping{
		DataCompanyPunishmentCreateCommandToDataCompanyPunishmentMapping instance = Mappers.getMapper( DataCompanyPunishmentCreateCommandToDataCompanyPunishmentMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPunishment
		 * @param dataCompanyPunishmentCreateCommand
		 */
		void fillDataCompanyPunishmentByDataCompanyPunishmentCreateCommand(@MappingTarget DataCompanyPunishment dataCompanyPunishment, DataCompanyPunishmentCreateCommand dataCompanyPunishmentCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyPunishmentGateway
	 */
	@Autowired
	public void setDataCompanyPunishmentGateway(DataCompanyPunishmentGateway dataCompanyPunishmentGateway) {
		this.dataCompanyPunishmentGateway = dataCompanyPunishmentGateway;
	}
}

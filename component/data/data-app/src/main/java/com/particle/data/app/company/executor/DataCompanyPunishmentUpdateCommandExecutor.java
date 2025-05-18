package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPunishmentAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPunishmentUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPunishmentVO;
import com.particle.data.domain.company.DataCompanyPunishment;
import com.particle.data.domain.company.DataCompanyPunishmentId;
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
 * 企业行政处罚 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyPunishmentUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPunishmentGateway dataCompanyPunishmentGateway;

	/**
	 * 执行 企业行政处罚 更新指令
	 * @param dataCompanyPunishmentUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPunishmentVO> execute(@Valid DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand) {
		DataCompanyPunishment dataCompanyPunishment = createByDataCompanyPunishmentUpdateCommand(dataCompanyPunishmentUpdateCommand);
		dataCompanyPunishment.setUpdateControl(dataCompanyPunishmentUpdateCommand);
		dataCompanyPunishment.initForUpdate();
		boolean save = dataCompanyPunishmentGateway.save(dataCompanyPunishment);
		if (save) {
			return SingleResponse.of(DataCompanyPunishmentAppStructMapping.instance.toDataCompanyPunishmentVO(dataCompanyPunishment));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业行政处罚更新指令创建企业行政处罚模型
	 * @param dataCompanyPunishmentUpdateCommand
	 * @return
	 */
	private DataCompanyPunishment createByDataCompanyPunishmentUpdateCommand(DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand){
		DataCompanyPunishment dataCompanyPunishment = DataCompanyPunishment.create();
		DataCompanyPunishmentUpdateCommandToDataCompanyPunishmentMapping.instance.fillDataCompanyPunishmentByDataCompanyPunishmentUpdateCommand(dataCompanyPunishment, dataCompanyPunishmentUpdateCommand);
		return dataCompanyPunishment;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyPunishmentUpdateCommandToDataCompanyPunishmentMapping{
		DataCompanyPunishmentUpdateCommandToDataCompanyPunishmentMapping instance = Mappers.getMapper(DataCompanyPunishmentUpdateCommandToDataCompanyPunishmentMapping.class );

		default DataCompanyPunishmentId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyPunishmentId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPunishment
		 * @param dataCompanyPunishmentUpdateCommand
		 */
		void fillDataCompanyPunishmentByDataCompanyPunishmentUpdateCommand(@MappingTarget DataCompanyPunishment dataCompanyPunishment, DataCompanyPunishmentUpdateCommand dataCompanyPunishmentUpdateCommand);
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

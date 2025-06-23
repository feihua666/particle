package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffPositionAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.domain.company.DataCompanyPrimeStaffPosition;
import com.particle.data.domain.company.DataCompanyPrimeStaffPositionId;
import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffPositionGateway;
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
 * 企业主要人员职位 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway;

	/**
	 * 执行 企业主要人员职位 更新指令
	 * @param dataCompanyPrimeStaffPositionUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionVO> execute(@Valid DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand) {
		DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition = createByDataCompanyPrimeStaffPositionUpdateCommand(dataCompanyPrimeStaffPositionUpdateCommand);
		dataCompanyPrimeStaffPosition.initForUpdate();
		dataCompanyPrimeStaffPosition.setUpdateControl(dataCompanyPrimeStaffPositionUpdateCommand);
		boolean save = dataCompanyPrimeStaffPositionGateway.save(dataCompanyPrimeStaffPosition);
		if (save) {
			return SingleResponse.of(DataCompanyPrimeStaffPositionAppStructMapping.instance.toDataCompanyPrimeStaffPositionVO(dataCompanyPrimeStaffPosition));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业主要人员职位更新指令创建企业主要人员职位模型
	 * @param dataCompanyPrimeStaffPositionUpdateCommand
	 * @return
	 */
	private DataCompanyPrimeStaffPosition createByDataCompanyPrimeStaffPositionUpdateCommand(DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand){
		DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition = DataCompanyPrimeStaffPosition.create();
		DataCompanyPrimeStaffPositionUpdateCommandToDataCompanyPrimeStaffPositionMapping.instance.fillDataCompanyPrimeStaffPositionByDataCompanyPrimeStaffPositionUpdateCommand(dataCompanyPrimeStaffPosition, dataCompanyPrimeStaffPositionUpdateCommand);
		return dataCompanyPrimeStaffPosition;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyPrimeStaffPositionUpdateCommandToDataCompanyPrimeStaffPositionMapping{
		DataCompanyPrimeStaffPositionUpdateCommandToDataCompanyPrimeStaffPositionMapping instance = Mappers.getMapper(DataCompanyPrimeStaffPositionUpdateCommandToDataCompanyPrimeStaffPositionMapping.class );

		default DataCompanyPrimeStaffPositionId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyPrimeStaffPositionId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPrimeStaffPosition
		 * @param dataCompanyPrimeStaffPositionUpdateCommand
		 */
		void fillDataCompanyPrimeStaffPositionByDataCompanyPrimeStaffPositionUpdateCommand(@MappingTarget DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition, DataCompanyPrimeStaffPositionUpdateCommand dataCompanyPrimeStaffPositionUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyPrimeStaffPositionGateway
	 */
	@Autowired
	public void setDataCompanyPrimeStaffPositionGateway(DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway) {
		this.dataCompanyPrimeStaffPositionGateway = dataCompanyPrimeStaffPositionGateway;
	}
}

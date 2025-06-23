package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffPositionAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffPositionCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffPositionVO;
import com.particle.data.domain.company.DataCompanyPrimeStaffPosition;
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
 * 企业主要人员职位 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-22 15:07:33
 */
@Component
@Validated
public class DataCompanyPrimeStaffPositionCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffPositionGateway dataCompanyPrimeStaffPositionGateway;

	/**
	 * 执行企业主要人员职位添加指令
	 * @param dataCompanyPrimeStaffPositionCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffPositionVO> execute(@Valid DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand) {
		DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition = createByDataCompanyPrimeStaffPositionCreateCommand(dataCompanyPrimeStaffPositionCreateCommand);
		dataCompanyPrimeStaffPosition.initForAdd();
		dataCompanyPrimeStaffPosition.setAddControl(dataCompanyPrimeStaffPositionCreateCommand);
		boolean save = dataCompanyPrimeStaffPositionGateway.save(dataCompanyPrimeStaffPosition);
		if (save) {
			return SingleResponse.of(DataCompanyPrimeStaffPositionAppStructMapping.instance.toDataCompanyPrimeStaffPositionVO(dataCompanyPrimeStaffPosition));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业主要人员职位创建指令创建企业主要人员职位模型
	 * @param dataCompanyPrimeStaffPositionCreateCommand
	 * @return
	 */
	private DataCompanyPrimeStaffPosition createByDataCompanyPrimeStaffPositionCreateCommand(DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand){
		DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition = DataCompanyPrimeStaffPosition.create();
		DataCompanyPrimeStaffPositionCreateCommandToDataCompanyPrimeStaffPositionMapping.instance.fillDataCompanyPrimeStaffPositionByDataCompanyPrimeStaffPositionCreateCommand(dataCompanyPrimeStaffPosition, dataCompanyPrimeStaffPositionCreateCommand);
		return dataCompanyPrimeStaffPosition;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyPrimeStaffPositionCreateCommandToDataCompanyPrimeStaffPositionMapping{
		DataCompanyPrimeStaffPositionCreateCommandToDataCompanyPrimeStaffPositionMapping instance = Mappers.getMapper( DataCompanyPrimeStaffPositionCreateCommandToDataCompanyPrimeStaffPositionMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPrimeStaffPosition
		 * @param dataCompanyPrimeStaffPositionCreateCommand
		 */
		void fillDataCompanyPrimeStaffPositionByDataCompanyPrimeStaffPositionCreateCommand(@MappingTarget DataCompanyPrimeStaffPosition dataCompanyPrimeStaffPosition, DataCompanyPrimeStaffPositionCreateCommand dataCompanyPrimeStaffPositionCreateCommand);
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

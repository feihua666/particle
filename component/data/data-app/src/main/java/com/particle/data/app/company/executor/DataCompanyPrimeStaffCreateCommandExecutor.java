package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.domain.company.DataCompanyPrimeStaff;
import com.particle.data.domain.company.gateway.DataCompanyPrimeStaffGateway;
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
 * 企业主要人员 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:18:44
 */
@Component
@Validated
public class DataCompanyPrimeStaffCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway;

	/**
	 * 执行企业主要人员添加指令
	 * @param dataCompanyPrimeStaffCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffVO> execute(@Valid DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand) {
		DataCompanyPrimeStaff dataCompanyPrimeStaff = createByDataCompanyPrimeStaffCreateCommand(dataCompanyPrimeStaffCreateCommand);
		dataCompanyPrimeStaff.initForAdd();
		dataCompanyPrimeStaff.setAddControl(dataCompanyPrimeStaffCreateCommand);
		boolean save = dataCompanyPrimeStaffGateway.save(dataCompanyPrimeStaff);
		if (save) {
			return SingleResponse.of(DataCompanyPrimeStaffAppStructMapping.instance.toDataCompanyPrimeStaffVO(dataCompanyPrimeStaff));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业主要人员创建指令创建企业主要人员模型
	 * @param dataCompanyPrimeStaffCreateCommand
	 * @return
	 */
	private DataCompanyPrimeStaff createByDataCompanyPrimeStaffCreateCommand(DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand){
		DataCompanyPrimeStaff dataCompanyPrimeStaff = DataCompanyPrimeStaff.create();
		DataCompanyPrimeStaffCreateCommandToDataCompanyPrimeStaffMapping.instance.fillDataCompanyPrimeStaffByDataCompanyPrimeStaffCreateCommand(dataCompanyPrimeStaff, dataCompanyPrimeStaffCreateCommand);
		return dataCompanyPrimeStaff;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyPrimeStaffCreateCommandToDataCompanyPrimeStaffMapping{
		DataCompanyPrimeStaffCreateCommandToDataCompanyPrimeStaffMapping instance = Mappers.getMapper( DataCompanyPrimeStaffCreateCommandToDataCompanyPrimeStaffMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPrimeStaff
		 * @param dataCompanyPrimeStaffCreateCommand
		 */
		void fillDataCompanyPrimeStaffByDataCompanyPrimeStaffCreateCommand(@MappingTarget DataCompanyPrimeStaff dataCompanyPrimeStaff, DataCompanyPrimeStaffCreateCommand dataCompanyPrimeStaffCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyPrimeStaffGateway
	 */
	@Autowired
	public void setDataCompanyPrimeStaffGateway(DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway) {
		this.dataCompanyPrimeStaffGateway = dataCompanyPrimeStaffGateway;
	}
}

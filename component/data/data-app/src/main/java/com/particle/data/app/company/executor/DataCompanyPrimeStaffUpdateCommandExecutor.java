package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPrimeStaffAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPrimeStaffUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPrimeStaffVO;
import com.particle.data.domain.company.DataCompanyPrimeStaff;
import com.particle.data.domain.company.DataCompanyPrimeStaffId;
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
 * 企业主要人员 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyPrimeStaffUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPrimeStaffGateway dataCompanyPrimeStaffGateway;

	/**
	 * 执行 企业主要人员 更新指令
	 * @param dataCompanyPrimeStaffUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPrimeStaffVO> execute(@Valid DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand) {
		DataCompanyPrimeStaff dataCompanyPrimeStaff = createByDataCompanyPrimeStaffUpdateCommand(dataCompanyPrimeStaffUpdateCommand);
		dataCompanyPrimeStaff.initForUpdate();
		dataCompanyPrimeStaff.setUpdateControl(dataCompanyPrimeStaffUpdateCommand);
		boolean save = dataCompanyPrimeStaffGateway.save(dataCompanyPrimeStaff);
		if (save) {
			return SingleResponse.of(DataCompanyPrimeStaffAppStructMapping.instance.toDataCompanyPrimeStaffVO(dataCompanyPrimeStaff));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业主要人员更新指令创建企业主要人员模型
	 * @param dataCompanyPrimeStaffUpdateCommand
	 * @return
	 */
	private DataCompanyPrimeStaff createByDataCompanyPrimeStaffUpdateCommand(DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand){
		DataCompanyPrimeStaff dataCompanyPrimeStaff = DataCompanyPrimeStaff.create();
		DataCompanyPrimeStaffUpdateCommandToDataCompanyPrimeStaffMapping.instance.fillDataCompanyPrimeStaffByDataCompanyPrimeStaffUpdateCommand(dataCompanyPrimeStaff, dataCompanyPrimeStaffUpdateCommand);
		return dataCompanyPrimeStaff;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyPrimeStaffUpdateCommandToDataCompanyPrimeStaffMapping{
		DataCompanyPrimeStaffUpdateCommandToDataCompanyPrimeStaffMapping instance = Mappers.getMapper(DataCompanyPrimeStaffUpdateCommandToDataCompanyPrimeStaffMapping.class );

		default DataCompanyPrimeStaffId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyPrimeStaffId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPrimeStaff
		 * @param dataCompanyPrimeStaffUpdateCommand
		 */
		void fillDataCompanyPrimeStaffByDataCompanyPrimeStaffUpdateCommand(@MappingTarget DataCompanyPrimeStaff dataCompanyPrimeStaff, DataCompanyPrimeStaffUpdateCommand dataCompanyPrimeStaffUpdateCommand);
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

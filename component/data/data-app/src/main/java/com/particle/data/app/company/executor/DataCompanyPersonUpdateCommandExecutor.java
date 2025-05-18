package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyPersonAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyPersonUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyPersonVO;
import com.particle.data.domain.company.DataCompanyPerson;
import com.particle.data.domain.company.DataCompanyPersonId;
import com.particle.data.domain.company.gateway.DataCompanyPersonGateway;
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
 * 企业个人 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyPersonUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyPersonGateway dataCompanyPersonGateway;

	/**
	 * 执行 企业个人 更新指令
	 * @param dataCompanyPersonUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyPersonVO> execute(@Valid DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand) {
		DataCompanyPerson dataCompanyPerson = createByDataCompanyPersonUpdateCommand(dataCompanyPersonUpdateCommand);
		dataCompanyPerson.initForUpdate();
		dataCompanyPerson.setUpdateControl(dataCompanyPersonUpdateCommand);
		boolean save = dataCompanyPersonGateway.save(dataCompanyPerson);
		if (save) {
			return SingleResponse.of(DataCompanyPersonAppStructMapping.instance.toDataCompanyPersonVO(dataCompanyPerson));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业个人更新指令创建企业个人模型
	 * @param dataCompanyPersonUpdateCommand
	 * @return
	 */
	private DataCompanyPerson createByDataCompanyPersonUpdateCommand(DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand){
		DataCompanyPerson dataCompanyPerson = DataCompanyPerson.create();
		DataCompanyPersonUpdateCommandToDataCompanyPersonMapping.instance.fillDataCompanyPersonByDataCompanyPersonUpdateCommand(dataCompanyPerson, dataCompanyPersonUpdateCommand);
		return dataCompanyPerson;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyPersonUpdateCommandToDataCompanyPersonMapping{
		DataCompanyPersonUpdateCommandToDataCompanyPersonMapping instance = Mappers.getMapper(DataCompanyPersonUpdateCommandToDataCompanyPersonMapping.class );

		default DataCompanyPersonId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyPersonId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyPerson
		 * @param dataCompanyPersonUpdateCommand
		 */
		void fillDataCompanyPersonByDataCompanyPersonUpdateCommand(@MappingTarget DataCompanyPerson dataCompanyPerson, DataCompanyPersonUpdateCommand dataCompanyPersonUpdateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyPersonGateway
	 */
	@Autowired
	public void setDataCompanyPersonGateway(DataCompanyPersonGateway dataCompanyPersonGateway) {
		this.dataCompanyPersonGateway = dataCompanyPersonGateway;
	}
}

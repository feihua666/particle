package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyAbnormalAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyAbnormalCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyAbnormalVO;
import com.particle.data.domain.company.DataCompanyAbnormal;
import com.particle.data.domain.company.gateway.DataCompanyAbnormalGateway;
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
 * 企业经营异常 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-05-29 10:47:31
 */
@Component
@Validated
public class DataCompanyAbnormalCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyAbnormalGateway dataCompanyAbnormalGateway;

	/**
	 * 执行企业经营异常添加指令
	 * @param dataCompanyAbnormalCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyAbnormalVO> execute(@Valid DataCompanyAbnormalCreateCommand dataCompanyAbnormalCreateCommand) {
		DataCompanyAbnormal dataCompanyAbnormal = createByDataCompanyAbnormalCreateCommand(dataCompanyAbnormalCreateCommand);
		dataCompanyAbnormal.setAddControl(dataCompanyAbnormalCreateCommand);
		boolean save = dataCompanyAbnormalGateway.save(dataCompanyAbnormal);
		if (save) {
			return SingleResponse.of(DataCompanyAbnormalAppStructMapping.instance.toDataCompanyAbnormalVO(dataCompanyAbnormal));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业经营异常创建指令创建企业经营异常模型
	 * @param dataCompanyAbnormalCreateCommand
	 * @return
	 */
	private DataCompanyAbnormal createByDataCompanyAbnormalCreateCommand(DataCompanyAbnormalCreateCommand dataCompanyAbnormalCreateCommand){
		DataCompanyAbnormal dataCompanyAbnormal = DataCompanyAbnormal.create();
		DataCompanyAbnormalCreateCommandToDataCompanyAbnormalMapping.instance.fillDataCompanyAbnormalByDataCompanyAbnormalCreateCommand(dataCompanyAbnormal, dataCompanyAbnormalCreateCommand);
		return dataCompanyAbnormal;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyAbnormalCreateCommandToDataCompanyAbnormalMapping{
		DataCompanyAbnormalCreateCommandToDataCompanyAbnormalMapping instance = Mappers.getMapper( DataCompanyAbnormalCreateCommandToDataCompanyAbnormalMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyAbnormal
		 * @param dataCompanyAbnormalCreateCommand
		 */
		void fillDataCompanyAbnormalByDataCompanyAbnormalCreateCommand(@MappingTarget DataCompanyAbnormal dataCompanyAbnormal, DataCompanyAbnormalCreateCommand dataCompanyAbnormalCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyAbnormalGateway
	 */
	@Autowired
	public void setDataCompanyAbnormalGateway(DataCompanyAbnormalGateway dataCompanyAbnormalGateway) {
		this.dataCompanyAbnormalGateway = dataCompanyAbnormalGateway;
	}
}

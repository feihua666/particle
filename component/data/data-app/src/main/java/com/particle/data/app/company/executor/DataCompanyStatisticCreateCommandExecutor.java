package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyStatisticCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.domain.company.DataCompanyStatistic;
import com.particle.data.domain.company.gateway.DataCompanyStatisticGateway;
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
 * 企业统计 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-04 15:53:01
 */
@Component
@Validated
public class DataCompanyStatisticCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyStatisticGateway dataCompanyStatisticGateway;

	/**
	 * 执行企业统计添加指令
	 * @param dataCompanyStatisticCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticVO> execute(@Valid DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand) {
		DataCompanyStatistic dataCompanyStatistic = createByDataCompanyStatisticCreateCommand(dataCompanyStatisticCreateCommand);
		dataCompanyStatistic.setAddControl(dataCompanyStatisticCreateCommand);
		boolean save = dataCompanyStatisticGateway.save(dataCompanyStatistic);
		if (save) {
			return SingleResponse.of(DataCompanyStatisticAppStructMapping.instance.toDataCompanyStatisticVO(dataCompanyStatistic));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业统计创建指令创建企业统计模型
	 * @param dataCompanyStatisticCreateCommand
	 * @return
	 */
	private DataCompanyStatistic createByDataCompanyStatisticCreateCommand(DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand){
		DataCompanyStatistic dataCompanyStatistic = DataCompanyStatistic.create();
		DataCompanyStatisticCreateCommandToDataCompanyStatisticMapping.instance.fillDataCompanyStatisticByDataCompanyStatisticCreateCommand(dataCompanyStatistic, dataCompanyStatisticCreateCommand);
		return dataCompanyStatistic;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyStatisticCreateCommandToDataCompanyStatisticMapping{
		DataCompanyStatisticCreateCommandToDataCompanyStatisticMapping instance = Mappers.getMapper( DataCompanyStatisticCreateCommandToDataCompanyStatisticMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyStatistic
		 * @param dataCompanyStatisticCreateCommand
		 */
		void fillDataCompanyStatisticByDataCompanyStatisticCreateCommand(@MappingTarget DataCompanyStatistic dataCompanyStatistic, DataCompanyStatisticCreateCommand dataCompanyStatisticCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyStatisticGateway
	 */
	@Autowired
	public void setDataCompanyStatisticGateway(DataCompanyStatisticGateway dataCompanyStatisticGateway) {
		this.dataCompanyStatisticGateway = dataCompanyStatisticGateway;
	}
}

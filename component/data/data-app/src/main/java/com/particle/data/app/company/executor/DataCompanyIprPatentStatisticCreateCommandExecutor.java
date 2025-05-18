package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.domain.company.DataCompanyIprPatentStatistic;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentStatisticGateway;
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
 * 企业知识产权专利统计 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:42:36
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentStatisticGateway dataCompanyIprPatentStatisticGateway;

	/**
	 * 执行企业知识产权专利统计添加指令
	 * @param dataCompanyIprPatentStatisticCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticVO> execute(@Valid DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand) {
		DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic = createByDataCompanyIprPatentStatisticCreateCommand(dataCompanyIprPatentStatisticCreateCommand);
		dataCompanyIprPatentStatistic.initForAdd();
		dataCompanyIprPatentStatistic.setAddControl(dataCompanyIprPatentStatisticCreateCommand);
		boolean save = dataCompanyIprPatentStatisticGateway.save(dataCompanyIprPatentStatistic);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentStatisticAppStructMapping.instance.toDataCompanyIprPatentStatisticVO(dataCompanyIprPatentStatistic));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利统计创建指令创建企业知识产权专利统计模型
	 * @param dataCompanyIprPatentStatisticCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentStatistic createByDataCompanyIprPatentStatisticCreateCommand(DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand){
		DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic = DataCompanyIprPatentStatistic.create();
		DataCompanyIprPatentStatisticCreateCommandToDataCompanyIprPatentStatisticMapping.instance.fillDataCompanyIprPatentStatisticByDataCompanyIprPatentStatisticCreateCommand(dataCompanyIprPatentStatistic, dataCompanyIprPatentStatisticCreateCommand);
		return dataCompanyIprPatentStatistic;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentStatisticCreateCommandToDataCompanyIprPatentStatisticMapping{
		DataCompanyIprPatentStatisticCreateCommandToDataCompanyIprPatentStatisticMapping instance = Mappers.getMapper( DataCompanyIprPatentStatisticCreateCommandToDataCompanyIprPatentStatisticMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentStatistic
		 * @param dataCompanyIprPatentStatisticCreateCommand
		 */
		void fillDataCompanyIprPatentStatisticByDataCompanyIprPatentStatisticCreateCommand(@MappingTarget DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic, DataCompanyIprPatentStatisticCreateCommand dataCompanyIprPatentStatisticCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentStatisticGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentStatisticGateway(DataCompanyIprPatentStatisticGateway dataCompanyIprPatentStatisticGateway) {
		this.dataCompanyIprPatentStatisticGateway = dataCompanyIprPatentStatisticGateway;
	}
}

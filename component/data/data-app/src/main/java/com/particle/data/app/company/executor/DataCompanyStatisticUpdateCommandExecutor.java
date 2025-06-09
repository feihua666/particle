package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyStatisticUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyStatisticVO;
import com.particle.data.domain.company.DataCompanyStatistic;
import com.particle.data.domain.company.DataCompanyStatisticId;
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
 * 企业统计 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyStatisticUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyStatisticGateway dataCompanyStatisticGateway;

	/**
	 * 执行 企业统计 更新指令
	 * @param dataCompanyStatisticUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyStatisticVO> execute(@Valid DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand) {
		DataCompanyStatistic dataCompanyStatistic = createByDataCompanyStatisticUpdateCommand(dataCompanyStatisticUpdateCommand);
		dataCompanyStatistic.setUpdateControl(dataCompanyStatisticUpdateCommand);
		boolean save = dataCompanyStatisticGateway.save(dataCompanyStatistic);
		if (save) {
			return SingleResponse.of(DataCompanyStatisticAppStructMapping.instance.toDataCompanyStatisticVO(dataCompanyStatistic));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业统计更新指令创建企业统计模型
	 * @param dataCompanyStatisticUpdateCommand
	 * @return
	 */
	private DataCompanyStatistic createByDataCompanyStatisticUpdateCommand(DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand){
		DataCompanyStatistic dataCompanyStatistic = DataCompanyStatistic.create();
		DataCompanyStatisticUpdateCommandToDataCompanyStatisticMapping.instance.fillDataCompanyStatisticByDataCompanyStatisticUpdateCommand(dataCompanyStatistic, dataCompanyStatisticUpdateCommand);
		return dataCompanyStatistic;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyStatisticUpdateCommandToDataCompanyStatisticMapping{
		DataCompanyStatisticUpdateCommandToDataCompanyStatisticMapping instance = Mappers.getMapper(DataCompanyStatisticUpdateCommandToDataCompanyStatisticMapping.class );

		default DataCompanyStatisticId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyStatisticId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyStatistic
		 * @param dataCompanyStatisticUpdateCommand
		 */
		void fillDataCompanyStatisticByDataCompanyStatisticUpdateCommand(@MappingTarget DataCompanyStatistic dataCompanyStatistic, DataCompanyStatisticUpdateCommand dataCompanyStatisticUpdateCommand);
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

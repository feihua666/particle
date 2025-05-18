package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentStatisticAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentStatisticUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentStatisticVO;
import com.particle.data.domain.company.DataCompanyIprPatentStatistic;
import com.particle.data.domain.company.DataCompanyIprPatentStatisticId;
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
 * 企业知识产权专利统计 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentStatisticUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentStatisticGateway dataCompanyIprPatentStatisticGateway;

	/**
	 * 执行 企业知识产权专利统计 更新指令
	 * @param dataCompanyIprPatentStatisticUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentStatisticVO> execute(@Valid DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand) {
		DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic = createByDataCompanyIprPatentStatisticUpdateCommand(dataCompanyIprPatentStatisticUpdateCommand);
		dataCompanyIprPatentStatistic.initForUpdate();
		dataCompanyIprPatentStatistic.setUpdateControl(dataCompanyIprPatentStatisticUpdateCommand);
		boolean save = dataCompanyIprPatentStatisticGateway.save(dataCompanyIprPatentStatistic);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentStatisticAppStructMapping.instance.toDataCompanyIprPatentStatisticVO(dataCompanyIprPatentStatistic));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利统计更新指令创建企业知识产权专利统计模型
	 * @param dataCompanyIprPatentStatisticUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentStatistic createByDataCompanyIprPatentStatisticUpdateCommand(DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand){
		DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic = DataCompanyIprPatentStatistic.create();
		DataCompanyIprPatentStatisticUpdateCommandToDataCompanyIprPatentStatisticMapping.instance.fillDataCompanyIprPatentStatisticByDataCompanyIprPatentStatisticUpdateCommand(dataCompanyIprPatentStatistic, dataCompanyIprPatentStatisticUpdateCommand);
		return dataCompanyIprPatentStatistic;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentStatisticUpdateCommandToDataCompanyIprPatentStatisticMapping{
		DataCompanyIprPatentStatisticUpdateCommandToDataCompanyIprPatentStatisticMapping instance = Mappers.getMapper(DataCompanyIprPatentStatisticUpdateCommandToDataCompanyIprPatentStatisticMapping.class );

		default DataCompanyIprPatentStatisticId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentStatisticId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentStatistic
		 * @param dataCompanyIprPatentStatisticUpdateCommand
		 */
		void fillDataCompanyIprPatentStatisticByDataCompanyIprPatentStatisticUpdateCommand(@MappingTarget DataCompanyIprPatentStatistic dataCompanyIprPatentStatistic, DataCompanyIprPatentStatisticUpdateCommand dataCompanyIprPatentStatisticUpdateCommand);
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

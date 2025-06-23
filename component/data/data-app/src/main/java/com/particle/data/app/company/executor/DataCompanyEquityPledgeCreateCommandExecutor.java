package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyEquityPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.domain.company.DataCompanyEquityPledge;
import com.particle.data.domain.company.gateway.DataCompanyEquityPledgeGateway;
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
 * 企业股权出质 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:10
 */
@Component
@Validated
public class DataCompanyEquityPledgeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway;

	/**
	 * 执行企业股权出质添加指令
	 * @param dataCompanyEquityPledgeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeVO> execute(@Valid DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand) {
		DataCompanyEquityPledge dataCompanyEquityPledge = createByDataCompanyEquityPledgeCreateCommand(dataCompanyEquityPledgeCreateCommand);
		dataCompanyEquityPledge.initForAdd();
		dataCompanyEquityPledge.setAddControl(dataCompanyEquityPledgeCreateCommand);
		boolean save = dataCompanyEquityPledgeGateway.save(dataCompanyEquityPledge);
		if (save) {
			return SingleResponse.of(DataCompanyEquityPledgeAppStructMapping.instance.toDataCompanyEquityPledgeVO(dataCompanyEquityPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业股权出质创建指令创建企业股权出质模型
	 * @param dataCompanyEquityPledgeCreateCommand
	 * @return
	 */
	private DataCompanyEquityPledge createByDataCompanyEquityPledgeCreateCommand(DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand){
		DataCompanyEquityPledge dataCompanyEquityPledge = DataCompanyEquityPledge.create();
		DataCompanyEquityPledgeCreateCommandToDataCompanyEquityPledgeMapping.instance.fillDataCompanyEquityPledgeByDataCompanyEquityPledgeCreateCommand(dataCompanyEquityPledge, dataCompanyEquityPledgeCreateCommand);
		return dataCompanyEquityPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyEquityPledgeCreateCommandToDataCompanyEquityPledgeMapping{
		DataCompanyEquityPledgeCreateCommandToDataCompanyEquityPledgeMapping instance = Mappers.getMapper( DataCompanyEquityPledgeCreateCommandToDataCompanyEquityPledgeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyEquityPledge
		 * @param dataCompanyEquityPledgeCreateCommand
		 */
		void fillDataCompanyEquityPledgeByDataCompanyEquityPledgeCreateCommand(@MappingTarget DataCompanyEquityPledge dataCompanyEquityPledge, DataCompanyEquityPledgeCreateCommand dataCompanyEquityPledgeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyEquityPledgeGateway
	 */
	@Autowired
	public void setDataCompanyEquityPledgeGateway(DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway) {
		this.dataCompanyEquityPledgeGateway = dataCompanyEquityPledgeGateway;
	}
}

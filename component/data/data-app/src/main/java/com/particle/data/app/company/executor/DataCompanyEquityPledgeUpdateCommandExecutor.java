package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyEquityPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyEquityPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyEquityPledgeVO;
import com.particle.data.domain.company.DataCompanyEquityPledge;
import com.particle.data.domain.company.DataCompanyEquityPledgeId;
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
 * 企业股权出质 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyEquityPledgeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyEquityPledgeGateway dataCompanyEquityPledgeGateway;

	/**
	 * 执行 企业股权出质 更新指令
	 * @param dataCompanyEquityPledgeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyEquityPledgeVO> execute(@Valid DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand) {
		DataCompanyEquityPledge dataCompanyEquityPledge = createByDataCompanyEquityPledgeUpdateCommand(dataCompanyEquityPledgeUpdateCommand);
		dataCompanyEquityPledge.initForUpdate();
		dataCompanyEquityPledge.setUpdateControl(dataCompanyEquityPledgeUpdateCommand);
		boolean save = dataCompanyEquityPledgeGateway.save(dataCompanyEquityPledge);
		if (save) {
			return SingleResponse.of(DataCompanyEquityPledgeAppStructMapping.instance.toDataCompanyEquityPledgeVO(dataCompanyEquityPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业股权出质更新指令创建企业股权出质模型
	 * @param dataCompanyEquityPledgeUpdateCommand
	 * @return
	 */
	private DataCompanyEquityPledge createByDataCompanyEquityPledgeUpdateCommand(DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand){
		DataCompanyEquityPledge dataCompanyEquityPledge = DataCompanyEquityPledge.create();
		DataCompanyEquityPledgeUpdateCommandToDataCompanyEquityPledgeMapping.instance.fillDataCompanyEquityPledgeByDataCompanyEquityPledgeUpdateCommand(dataCompanyEquityPledge, dataCompanyEquityPledgeUpdateCommand);
		return dataCompanyEquityPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyEquityPledgeUpdateCommandToDataCompanyEquityPledgeMapping{
		DataCompanyEquityPledgeUpdateCommandToDataCompanyEquityPledgeMapping instance = Mappers.getMapper(DataCompanyEquityPledgeUpdateCommandToDataCompanyEquityPledgeMapping.class );

		default DataCompanyEquityPledgeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyEquityPledgeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyEquityPledge
		 * @param dataCompanyEquityPledgeUpdateCommand
		 */
		void fillDataCompanyEquityPledgeByDataCompanyEquityPledgeUpdateCommand(@MappingTarget DataCompanyEquityPledge dataCompanyEquityPledge, DataCompanyEquityPledgeUpdateCommand dataCompanyEquityPledgeUpdateCommand);
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

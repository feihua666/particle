package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledge;
import com.particle.data.domain.company.gateway.DataCompanyIprTrademarkPledgeGateway;
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
 * 企业知识产权商标质押信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-16 11:15:53
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway;

	/**
	 * 执行企业知识产权商标质押信息添加指令
	 * @param dataCompanyIprTrademarkPledgeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeVO> execute(@Valid DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand) {
		DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge = createByDataCompanyIprTrademarkPledgeCreateCommand(dataCompanyIprTrademarkPledgeCreateCommand);
		dataCompanyIprTrademarkPledge.initForAdd();
		dataCompanyIprTrademarkPledge.setAddControl(dataCompanyIprTrademarkPledgeCreateCommand);
		boolean save = dataCompanyIprTrademarkPledgeGateway.save(dataCompanyIprTrademarkPledge);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkPledgeAppStructMapping.instance.toDataCompanyIprTrademarkPledgeVO(dataCompanyIprTrademarkPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标质押信息创建指令创建企业知识产权商标质押信息模型
	 * @param dataCompanyIprTrademarkPledgeCreateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkPledge createByDataCompanyIprTrademarkPledgeCreateCommand(DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand){
		DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge = DataCompanyIprTrademarkPledge.create();
		DataCompanyIprTrademarkPledgeCreateCommandToDataCompanyIprTrademarkPledgeMapping.instance.fillDataCompanyIprTrademarkPledgeByDataCompanyIprTrademarkPledgeCreateCommand(dataCompanyIprTrademarkPledge, dataCompanyIprTrademarkPledgeCreateCommand);
		return dataCompanyIprTrademarkPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprTrademarkPledgeCreateCommandToDataCompanyIprTrademarkPledgeMapping{
		DataCompanyIprTrademarkPledgeCreateCommandToDataCompanyIprTrademarkPledgeMapping instance = Mappers.getMapper( DataCompanyIprTrademarkPledgeCreateCommandToDataCompanyIprTrademarkPledgeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkPledge
		 * @param dataCompanyIprTrademarkPledgeCreateCommand
		 */
		void fillDataCompanyIprTrademarkPledgeByDataCompanyIprTrademarkPledgeCreateCommand(@MappingTarget DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge, DataCompanyIprTrademarkPledgeCreateCommand dataCompanyIprTrademarkPledgeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprTrademarkPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprTrademarkPledgeGateway(DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway) {
		this.dataCompanyIprTrademarkPledgeGateway = dataCompanyIprTrademarkPledgeGateway;
	}
}

package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.domain.company.DataCompanyIprPledge;
import com.particle.data.domain.company.gateway.DataCompanyIprPledgeGateway;
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
 * 企业知识产权出质 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-06-20 16:19:21
 */
@Component
@Validated
public class DataCompanyIprPledgeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway;

	/**
	 * 执行企业知识产权出质添加指令
	 * @param dataCompanyIprPledgeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeVO> execute(@Valid DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand) {
		DataCompanyIprPledge dataCompanyIprPledge = createByDataCompanyIprPledgeCreateCommand(dataCompanyIprPledgeCreateCommand);
		dataCompanyIprPledge.initForAdd();
		dataCompanyIprPledge.setAddControl(dataCompanyIprPledgeCreateCommand);
		boolean save = dataCompanyIprPledgeGateway.save(dataCompanyIprPledge);
		if (save) {
			return SingleResponse.of(DataCompanyIprPledgeAppStructMapping.instance.toDataCompanyIprPledgeVO(dataCompanyIprPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权出质创建指令创建企业知识产权出质模型
	 * @param dataCompanyIprPledgeCreateCommand
	 * @return
	 */
	private DataCompanyIprPledge createByDataCompanyIprPledgeCreateCommand(DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand){
		DataCompanyIprPledge dataCompanyIprPledge = DataCompanyIprPledge.create();
		DataCompanyIprPledgeCreateCommandToDataCompanyIprPledgeMapping.instance.fillDataCompanyIprPledgeByDataCompanyIprPledgeCreateCommand(dataCompanyIprPledge, dataCompanyIprPledgeCreateCommand);
		return dataCompanyIprPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPledgeCreateCommandToDataCompanyIprPledgeMapping{
		DataCompanyIprPledgeCreateCommandToDataCompanyIprPledgeMapping instance = Mappers.getMapper( DataCompanyIprPledgeCreateCommandToDataCompanyIprPledgeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPledge
		 * @param dataCompanyIprPledgeCreateCommand
		 */
		void fillDataCompanyIprPledgeByDataCompanyIprPledgeCreateCommand(@MappingTarget DataCompanyIprPledge dataCompanyIprPledge, DataCompanyIprPledgeCreateCommand dataCompanyIprPledgeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprPledgeGateway(DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway) {
		this.dataCompanyIprPledgeGateway = dataCompanyIprPledgeGateway;
	}
}

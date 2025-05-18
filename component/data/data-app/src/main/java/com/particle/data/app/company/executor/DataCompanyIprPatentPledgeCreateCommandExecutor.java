package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeCreateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.domain.company.DataCompanyIprPatentPledge;
import com.particle.data.domain.company.gateway.DataCompanyIprPatentPledgeGateway;
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
 * 企业知识产权专利质押信息 创建指令执行器
 * </p>
 *
 * @author yw
 * @since 2025-04-05 16:41:40
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeCreateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway;

	/**
	 * 执行企业知识产权专利质押信息添加指令
	 * @param dataCompanyIprPatentPledgeCreateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeVO> execute(@Valid DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand) {
		DataCompanyIprPatentPledge dataCompanyIprPatentPledge = createByDataCompanyIprPatentPledgeCreateCommand(dataCompanyIprPatentPledgeCreateCommand);
		dataCompanyIprPatentPledge.initForAdd();
		dataCompanyIprPatentPledge.setAddControl(dataCompanyIprPatentPledgeCreateCommand);
		boolean save = dataCompanyIprPatentPledgeGateway.save(dataCompanyIprPatentPledge);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentPledgeAppStructMapping.instance.toDataCompanyIprPatentPledgeVO(dataCompanyIprPatentPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利质押信息创建指令创建企业知识产权专利质押信息模型
	 * @param dataCompanyIprPatentPledgeCreateCommand
	 * @return
	 */
	private DataCompanyIprPatentPledge createByDataCompanyIprPatentPledgeCreateCommand(DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand){
		DataCompanyIprPatentPledge dataCompanyIprPatentPledge = DataCompanyIprPatentPledge.create();
		DataCompanyIprPatentPledgeCreateCommandToDataCompanyIprPatentPledgeMapping.instance.fillDataCompanyIprPatentPledgeByDataCompanyIprPatentPledgeCreateCommand(dataCompanyIprPatentPledge, dataCompanyIprPatentPledgeCreateCommand);
		return dataCompanyIprPatentPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface  DataCompanyIprPatentPledgeCreateCommandToDataCompanyIprPatentPledgeMapping{
		DataCompanyIprPatentPledgeCreateCommandToDataCompanyIprPatentPledgeMapping instance = Mappers.getMapper( DataCompanyIprPatentPledgeCreateCommandToDataCompanyIprPatentPledgeMapping.class );

		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentPledge
		 * @param dataCompanyIprPatentPledgeCreateCommand
		 */
		void fillDataCompanyIprPatentPledgeByDataCompanyIprPatentPledgeCreateCommand(@MappingTarget DataCompanyIprPatentPledge dataCompanyIprPatentPledge, DataCompanyIprPatentPledgeCreateCommand dataCompanyIprPatentPledgeCreateCommand);
	}

	/**
	 * 注入使用set方法
	 * @param dataCompanyIprPatentPledgeGateway
	 */
	@Autowired
	public void setDataCompanyIprPatentPledgeGateway(DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway) {
		this.dataCompanyIprPatentPledgeGateway = dataCompanyIprPatentPledgeGateway;
	}
}

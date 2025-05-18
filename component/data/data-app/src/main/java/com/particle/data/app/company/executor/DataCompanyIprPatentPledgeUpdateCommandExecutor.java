package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPatentPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPatentPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPatentPledgeVO;
import com.particle.data.domain.company.DataCompanyIprPatentPledge;
import com.particle.data.domain.company.DataCompanyIprPatentPledgeId;
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
 * 企业知识产权专利质押信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPatentPledgeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPatentPledgeGateway dataCompanyIprPatentPledgeGateway;

	/**
	 * 执行 企业知识产权专利质押信息 更新指令
	 * @param dataCompanyIprPatentPledgeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPatentPledgeVO> execute(@Valid DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand) {
		DataCompanyIprPatentPledge dataCompanyIprPatentPledge = createByDataCompanyIprPatentPledgeUpdateCommand(dataCompanyIprPatentPledgeUpdateCommand);
		dataCompanyIprPatentPledge.initForUpdate();
		dataCompanyIprPatentPledge.setUpdateControl(dataCompanyIprPatentPledgeUpdateCommand);
		boolean save = dataCompanyIprPatentPledgeGateway.save(dataCompanyIprPatentPledge);
		if (save) {
			return SingleResponse.of(DataCompanyIprPatentPledgeAppStructMapping.instance.toDataCompanyIprPatentPledgeVO(dataCompanyIprPatentPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权专利质押信息更新指令创建企业知识产权专利质押信息模型
	 * @param dataCompanyIprPatentPledgeUpdateCommand
	 * @return
	 */
	private DataCompanyIprPatentPledge createByDataCompanyIprPatentPledgeUpdateCommand(DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand){
		DataCompanyIprPatentPledge dataCompanyIprPatentPledge = DataCompanyIprPatentPledge.create();
		DataCompanyIprPatentPledgeUpdateCommandToDataCompanyIprPatentPledgeMapping.instance.fillDataCompanyIprPatentPledgeByDataCompanyIprPatentPledgeUpdateCommand(dataCompanyIprPatentPledge, dataCompanyIprPatentPledgeUpdateCommand);
		return dataCompanyIprPatentPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPatentPledgeUpdateCommandToDataCompanyIprPatentPledgeMapping{
		DataCompanyIprPatentPledgeUpdateCommandToDataCompanyIprPatentPledgeMapping instance = Mappers.getMapper(DataCompanyIprPatentPledgeUpdateCommandToDataCompanyIprPatentPledgeMapping.class );

		default DataCompanyIprPatentPledgeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPatentPledgeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPatentPledge
		 * @param dataCompanyIprPatentPledgeUpdateCommand
		 */
		void fillDataCompanyIprPatentPledgeByDataCompanyIprPatentPledgeUpdateCommand(@MappingTarget DataCompanyIprPatentPledge dataCompanyIprPatentPledge, DataCompanyIprPatentPledgeUpdateCommand dataCompanyIprPatentPledgeUpdateCommand);
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

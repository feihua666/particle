package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprPledgeVO;
import com.particle.data.domain.company.DataCompanyIprPledge;
import com.particle.data.domain.company.DataCompanyIprPledgeId;
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
 * 企业知识产权出质 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprPledgeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprPledgeGateway dataCompanyIprPledgeGateway;

	/**
	 * 执行 企业知识产权出质 更新指令
	 * @param dataCompanyIprPledgeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprPledgeVO> execute(@Valid DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand) {
		DataCompanyIprPledge dataCompanyIprPledge = createByDataCompanyIprPledgeUpdateCommand(dataCompanyIprPledgeUpdateCommand);
		dataCompanyIprPledge.initForUpdate();
		dataCompanyIprPledge.setUpdateControl(dataCompanyIprPledgeUpdateCommand);
		boolean save = dataCompanyIprPledgeGateway.save(dataCompanyIprPledge);
		if (save) {
			return SingleResponse.of(DataCompanyIprPledgeAppStructMapping.instance.toDataCompanyIprPledgeVO(dataCompanyIprPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权出质更新指令创建企业知识产权出质模型
	 * @param dataCompanyIprPledgeUpdateCommand
	 * @return
	 */
	private DataCompanyIprPledge createByDataCompanyIprPledgeUpdateCommand(DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand){
		DataCompanyIprPledge dataCompanyIprPledge = DataCompanyIprPledge.create();
		DataCompanyIprPledgeUpdateCommandToDataCompanyIprPledgeMapping.instance.fillDataCompanyIprPledgeByDataCompanyIprPledgeUpdateCommand(dataCompanyIprPledge, dataCompanyIprPledgeUpdateCommand);
		return dataCompanyIprPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprPledgeUpdateCommandToDataCompanyIprPledgeMapping{
		DataCompanyIprPledgeUpdateCommandToDataCompanyIprPledgeMapping instance = Mappers.getMapper(DataCompanyIprPledgeUpdateCommandToDataCompanyIprPledgeMapping.class );

		default DataCompanyIprPledgeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprPledgeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprPledge
		 * @param dataCompanyIprPledgeUpdateCommand
		 */
		void fillDataCompanyIprPledgeByDataCompanyIprPledgeUpdateCommand(@MappingTarget DataCompanyIprPledge dataCompanyIprPledge, DataCompanyIprPledgeUpdateCommand dataCompanyIprPledgeUpdateCommand);
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

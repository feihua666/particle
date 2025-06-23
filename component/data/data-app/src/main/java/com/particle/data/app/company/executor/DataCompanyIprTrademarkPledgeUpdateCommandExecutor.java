package com.particle.data.app.company.executor;

import com.particle.data.app.company.structmapping.DataCompanyIprTrademarkPledgeAppStructMapping;
import com.particle.data.client.company.dto.command.DataCompanyIprTrademarkPledgeUpdateCommand;
import com.particle.data.client.company.dto.data.DataCompanyIprTrademarkPledgeVO;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledge;
import com.particle.data.domain.company.DataCompanyIprTrademarkPledgeId;
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
 * 企业知识产权商标质押信息 更新指令执行器
 * </p>
 *
 * @author yw
 * @since 2023-01-03
 */
@Component
@Validated
public class DataCompanyIprTrademarkPledgeUpdateCommandExecutor  extends AbstractBaseExecutor {

	private DataCompanyIprTrademarkPledgeGateway dataCompanyIprTrademarkPledgeGateway;

	/**
	 * 执行 企业知识产权商标质押信息 更新指令
	 * @param dataCompanyIprTrademarkPledgeUpdateCommand
	 * @return
	 */
	public SingleResponse<DataCompanyIprTrademarkPledgeVO> execute(@Valid DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand) {
		DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge = createByDataCompanyIprTrademarkPledgeUpdateCommand(dataCompanyIprTrademarkPledgeUpdateCommand);
		dataCompanyIprTrademarkPledge.initForUpdate();
		dataCompanyIprTrademarkPledge.setUpdateControl(dataCompanyIprTrademarkPledgeUpdateCommand);
		boolean save = dataCompanyIprTrademarkPledgeGateway.save(dataCompanyIprTrademarkPledge);
		if (save) {
			return SingleResponse.of(DataCompanyIprTrademarkPledgeAppStructMapping.instance.toDataCompanyIprTrademarkPledgeVO(dataCompanyIprTrademarkPledge));
		}
		return SingleResponse.buildFailure(ErrorCodeGlobalEnum.SAVE_ERROR);
	}

	/**
	 * 根据企业知识产权商标质押信息更新指令创建企业知识产权商标质押信息模型
	 * @param dataCompanyIprTrademarkPledgeUpdateCommand
	 * @return
	 */
	private DataCompanyIprTrademarkPledge createByDataCompanyIprTrademarkPledgeUpdateCommand(DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand){
		DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge = DataCompanyIprTrademarkPledge.create();
		DataCompanyIprTrademarkPledgeUpdateCommandToDataCompanyIprTrademarkPledgeMapping.instance.fillDataCompanyIprTrademarkPledgeByDataCompanyIprTrademarkPledgeUpdateCommand(dataCompanyIprTrademarkPledge, dataCompanyIprTrademarkPledgeUpdateCommand);
		return dataCompanyIprTrademarkPledge;
	}

	@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
	interface DataCompanyIprTrademarkPledgeUpdateCommandToDataCompanyIprTrademarkPledgeMapping{
		DataCompanyIprTrademarkPledgeUpdateCommandToDataCompanyIprTrademarkPledgeMapping instance = Mappers.getMapper(DataCompanyIprTrademarkPledgeUpdateCommandToDataCompanyIprTrademarkPledgeMapping.class );

		default DataCompanyIprTrademarkPledgeId map(Long id){
			if (id == null) {
				return null;
			}
			return DataCompanyIprTrademarkPledgeId.of(id);
		}
		/**
		 * 同名属性会自动映射，包括枚举
		 * @param dataCompanyIprTrademarkPledge
		 * @param dataCompanyIprTrademarkPledgeUpdateCommand
		 */
		void fillDataCompanyIprTrademarkPledgeByDataCompanyIprTrademarkPledgeUpdateCommand(@MappingTarget DataCompanyIprTrademarkPledge dataCompanyIprTrademarkPledge, DataCompanyIprTrademarkPledgeUpdateCommand dataCompanyIprTrademarkPledgeUpdateCommand);
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
